package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.*;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteGeoLocalisation;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.InformationCadastrale;
import com.amperus.prospection.businesslogic.models.Mandat;
import com.amperus.prospection.businesslogic.models.Syndicat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JpaCoproprieteStorage implements CoproprieteRepository {

    private final SpringCoproprieteRepository springCoproprieteRepository;
    private final JpaVilleStorage jpaVilleStorage;
    private final JpaSyndicatStorage jpaSyndicatStorage;

    public JpaCoproprieteStorage(SpringCoproprieteRepository springCoproprieteRepository, JpaVilleStorage jpaVilleStorage, JpaSyndicatStorage jpaSyndicatStorage) {
        this.springCoproprieteRepository = springCoproprieteRepository;
        this.jpaVilleStorage = jpaVilleStorage;
        this.jpaSyndicatStorage = jpaSyndicatStorage;
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
    public List<CoproprieteGeoLocalisation> findAllWithMissingNumeroEtVoie() {
        return springCoproprieteRepository.findAllWithMissingNumeroEtVoie();
    }

    @Override
    public void updateNumeroEtVoie(String numeroImmatriculation, String street) {
        springCoproprieteRepository.updateNumeroEtVoieForNumeroImmatriculation(street, numeroImmatriculation);
    }

}