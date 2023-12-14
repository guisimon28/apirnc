package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.CoproprieteJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.InformationCadastraleJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.MandatJpaEntity;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JpaCoproprieteStorage implements CoproprieteRepository {

    private final SpringCoproprieteRepository springCoproprieteRepository;
    private final SpringMandatJpaRepository springMandatJpaRepository;
    private final SpringInformationsCadastralesJpaRepository springInformationsCadastralesJpaRepository;
    private final JpaVilleStorage jpaVilleStorage;
    private final JpaSyndicatStorage jpaSyndicatStorage;

    public JpaCoproprieteStorage(SpringCoproprieteRepository springCoproprieteRepository, SpringMandatJpaRepository springMandatJpaRepository,
                                 SpringInformationsCadastralesJpaRepository springInformationsCadastralesJpaRepository,
                                 JpaVilleStorage jpaVilleStorage, JpaSyndicatStorage jpaSyndicatStorage) {
        this.springCoproprieteRepository = springCoproprieteRepository;
        this.springMandatJpaRepository = springMandatJpaRepository;
        this.springInformationsCadastralesJpaRepository = springInformationsCadastralesJpaRepository;
        this.jpaVilleStorage = jpaVilleStorage;
        this.jpaSyndicatStorage = jpaSyndicatStorage;
    }

    @Transactional
    @Override
    public void saveAll(List<Copropriete> coproprietes) {
        updateAllSyndicats(coproprietes);
        updateAllVilles(coproprietes);
        coproprietes.parallelStream().forEach(this::createOrUpdate);
    }

    private void updateAllSyndicats(List<Copropriete> coproprietes) {
        List<Syndicat> syndicats = coproprietes.stream().map(Copropriete::mandat)
                .filter(Objects::nonNull).map(Mandat::syndicat)
                .filter(Objects::nonNull).distinct().toList();
        jpaSyndicatStorage.saveAll(syndicats);
    }

    private void updateAllVilles(List<Copropriete> coproprietes) {
        List<Ville> villes = coproprietes.stream().map(Copropriete::adresse)
                .filter(Objects::nonNull).map(Adresse::ville)
                .filter(Objects::nonNull).distinct().toList();
        jpaVilleStorage.saveAll(villes);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void createOrUpdate(Copropriete copropriete) {
        CoproprieteJpaEntity coproprieteJpaEntity = springCoproprieteRepository.findByNumeroImmatriculation(copropriete.numeroImmatriculation())
                .orElseGet(CoproprieteJpaEntity::new);
        coproprieteJpaEntity.update(copropriete);
        jpaVilleStorage.find(copropriete.adresse().ville()).ifPresent(coproprieteJpaEntity::setVille);
        coproprieteJpaEntity = springCoproprieteRepository.save(coproprieteJpaEntity);
        createOrUpdateInformationsCadastrales(coproprieteJpaEntity, copropriete);
        createOrUpdateMandat(copropriete.mandat(), coproprieteJpaEntity);
    }

    private void createOrUpdateInformationsCadastrales(CoproprieteJpaEntity coproprieteJpaEntity, Copropriete copropriete) {
        if (coproprieteJpaEntity.getInformationsCadastrales() == null) {
            coproprieteJpaEntity.setInformationsCadastrales(new ArrayList<>());
        }
        copropriete.informationsCadastrales().forEach(info -> {
            InformationCadastraleJpaEntity informationCadastrale =
                    springInformationsCadastralesJpaRepository.findByReference(info.reference()).orElseGet(InformationCadastraleJpaEntity::new);
            informationCadastrale.update(info);
            informationCadastrale.setCopropriete(coproprieteJpaEntity);
            springInformationsCadastralesJpaRepository.save(informationCadastrale);
            coproprieteJpaEntity.getInformationsCadastrales().add(informationCadastrale);
        });
    }

    private void createOrUpdateMandat(Mandat mandat, CoproprieteJpaEntity coproprieteJpaEntity) {
        if (mandat != null && mandat.syndicat() != null) {
            jpaSyndicatStorage.findBySiret(mandat.syndicat().siret()).ifPresent(s -> {
                MandatJpaEntity mandatJpaEntity = new MandatJpaEntity();
                mandatJpaEntity.update(mandat);
                mandatJpaEntity.setCopropriete(coproprieteJpaEntity);
                mandatJpaEntity.setSyndicat(s);
                mandatJpaEntity = springMandatJpaRepository.save(mandatJpaEntity);
                if (coproprieteJpaEntity.getMandats() == null) {
                    coproprieteJpaEntity.setMandats(new ArrayList<>());
                }
                coproprieteJpaEntity.getMandats().add(mandatJpaEntity);
            });
        }
    }

}