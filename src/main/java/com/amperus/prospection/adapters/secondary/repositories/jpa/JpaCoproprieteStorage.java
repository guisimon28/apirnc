package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.*;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.InformationCadastrale;
import com.amperus.prospection.businesslogic.models.Mandat;
import com.amperus.prospection.businesslogic.models.Syndicat;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPage;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPageable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JpaCoproprieteStorage implements CoproprieteRepository {

    private final SpringCoproprieteRepository springCoproprieteRepository;
    private final JpaVilleStorage jpaVilleStorage;
    private final JpaSyndicatStorage jpaSyndicatStorage;
    private final EntityManager entityManager;

    public JpaCoproprieteStorage(SpringCoproprieteRepository springCoproprieteRepository, JpaVilleStorage jpaVilleStorage,
                                 JpaSyndicatStorage jpaSyndicatStorage, EntityManager entityManager) {
        this.springCoproprieteRepository = springCoproprieteRepository;
        this.jpaVilleStorage = jpaVilleStorage;
        this.jpaSyndicatStorage = jpaSyndicatStorage;
        this.entityManager = entityManager;
    }

    @Override
    public void saveAll(List<Copropriete> coproprietes) {
        var allSyndicats = jpaSyndicatStorage.updateAndGetAllSyndicats(coproprietes);
        var allVilles = jpaVilleStorage.updateAndGetAllVilles(coproprietes);
        coproprietes.parallelStream().forEach(copro -> createOrUpdate(copro, allVilles, allSyndicats));
    }

    public void createOrUpdate(Copropriete copropriete, List<VilleJpaEntity> villeJpaEntities, List<SyndicatJpaEntity> syndicatJpaEntities) {
        CoproprieteJpaEntity coproprieteJpaEntity = springCoproprieteRepository.findByNumeroImmatriculation(copropriete.numeroImmatriculation())
                .orElseGet(CoproprieteJpaEntity::new);
        coproprieteJpaEntity.update(copropriete);
        jpaVilleStorage.find(copropriete.adresse().ville(), villeJpaEntities).ifPresent(coproprieteJpaEntity::setVille);
        createOrUpdateInformationsCadastrales(coproprieteJpaEntity, copropriete);
        coproprieteJpaEntity = springCoproprieteRepository.save(coproprieteJpaEntity);
        createOrUpdateMandat(copropriete.mandat(), coproprieteJpaEntity, syndicatJpaEntities);
    }

    private void createOrUpdateInformationsCadastrales(CoproprieteJpaEntity coproprieteJpaEntity, Copropriete copropriete) {
        if (coproprieteJpaEntity.getInformationsCadastrales() == null) {
            coproprieteJpaEntity.setInformationsCadastrales(new ArrayList<>());
        }
        List<InformationCadastraleJpaEntity> currentInfos = coproprieteJpaEntity.getInformationsCadastrales();
        copropriete.informationsCadastrales().stream()
                .map(info -> findOrCreateInfoCadastrale(currentInfos, info))
                .forEach(currentInfos::add);
    }

    private InformationCadastraleJpaEntity findOrCreateInfoCadastrale(List<InformationCadastraleJpaEntity> currentInfos, InformationCadastrale info) {
        var newInfo = currentInfos.stream()
                .filter(i -> i.getReference().equals(info.reference()))
                .findFirst()
                .orElseGet(InformationCadastraleJpaEntity::new);
        newInfo.update(info);
        return newInfo;
    }

    private void createOrUpdateMandat(Mandat mandat, CoproprieteJpaEntity coproprieteJpaEntity, List<SyndicatJpaEntity> syndicatJpaEntities) {
        if (mandat == null || mandat.syndicat() == null) {
            return;
        }
        if (coproprieteJpaEntity.getMandats() == null) {
            coproprieteJpaEntity.setMandats(new ArrayList<>());
        }
        MandatJpaEntity mandatJpa = findExistingOrCreateNewMandat(coproprieteJpaEntity, mandat.syndicat());
        mandatJpa.update(mandat);
        jpaSyndicatStorage.find(mandat.syndicat(), syndicatJpaEntities).ifPresent(mandatJpa::setSyndicat);
        mandatJpa.setCopropriete(coproprieteJpaEntity);
        coproprieteJpaEntity.addMandat(mandatJpa);
    }

    private MandatJpaEntity findExistingOrCreateNewMandat(CoproprieteJpaEntity coproprieteJpaEntity, Syndicat syndicat) {
        return coproprieteJpaEntity.getMandats().stream()
                .filter(m -> m.getSyndicat() != null && m.getSyndicat().isSame(syndicat))
                .findFirst()
                .orElseGet(MandatJpaEntity::new);
    }

    @Override
    public Optional<Copropriete> findByNumeroImmatriculation(String numeroImmatriculation) {
        var jpaEntity = springCoproprieteRepository.findByNumeroImmatriculation(numeroImmatriculation);
        return jpaEntity.map(CoproprieteJpaEntity::convertToDomain);
    }

    @Override
    public MyAppPage<Copropriete> findAllCoproprietes(MyAppPageable pagination) {
        return MyAppPages.from(findAllWithPagination(pagination), CoproprieteJpaEntity::convertToDomain);
    }

    public Page<CoproprieteJpaEntity> findAllWithPagination(MyAppPageable pagination) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CoproprieteJpaEntity> cq = cb.createQuery(CoproprieteJpaEntity.class);

        Root<CoproprieteJpaEntity> root = cq.from(CoproprieteJpaEntity.class);
        applyFilter(pagination, cb, root, cq);

        CriteriaQuery<CoproprieteJpaEntity> select = cq.select(root);

        TypedQuery<CoproprieteJpaEntity> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult((pagination.getPage() - 1) * pagination.getPageSize());
        typedQuery.setMaxResults(pagination.getPageSize());

        List<CoproprieteJpaEntity> list = typedQuery.getResultList();

        // Comptez le total des éléments pour la pagination
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(CoproprieteJpaEntity.class)));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(list, PageRequest.of(pagination.getPage() - 1, pagination.getPageSize()), count);
    }

    private static void applyFilter(MyAppPageable pagination, CriteriaBuilder cb, Root<CoproprieteJpaEntity> root, CriteriaQuery<CoproprieteJpaEntity> cq) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotBlank(pagination.getSearchTerm())) {
            String likePattern = "%" + pagination.getSearchTerm().toLowerCase() + "%";

            Predicate nomUsagePredicate = cb.like(cb.lower(root.get("nomUsage")), likePattern);
            Predicate numeroImmatriculationPredicate = cb.like(cb.lower(root.get("numeroImmatriculation")), likePattern);
            Predicate numeroEtVoiePredicate = cb.like(cb.lower(root.get("adresse").get("numeroEtVoie")), likePattern);
            Predicate villeNomPredicate = cb.like(cb.lower(root.get("ville").get("nom")), likePattern);

            predicates.add(nomUsagePredicate);
            predicates.add(numeroImmatriculationPredicate);
            predicates.add(numeroEtVoiePredicate);
            predicates.add(villeNomPredicate);

            Join<CoproprieteJpaEntity, MandatJpaEntity> mandatJoin = root.join("mandats", JoinType.LEFT);
            Join<MandatJpaEntity, SyndicatJpaEntity> syndicatJoin = mandatJoin.join("syndicat", JoinType.LEFT);
            Predicate syndicatNomPredicate = cb.like(cb.lower(syndicatJoin.get("raisonSociale")), likePattern);
            predicates.add(syndicatNomPredicate);
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.or(predicates.toArray(new Predicate[0])));
        }
    }

}