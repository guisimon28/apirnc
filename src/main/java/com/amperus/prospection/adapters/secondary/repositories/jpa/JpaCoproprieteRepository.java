package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.*;
import com.amperus.prospection.adapters.secondary.repositories.jpa.mappers.CoproprieteMapper;
import com.amperus.prospection.adapters.secondary.repositories.jpa.mappers.MandatMapper;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Cadastre;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.Mandat;
import com.amperus.prospection.businesslogic.models.Ville;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JpaCoproprieteRepository implements CoproprieteRepository {

    private final SpringCoproprieteRepository springCoproprieteRepository;
    private final SpringVilleJpaRepository springVilleJpaRepository;
    private final SpringDepartementJpaRepository springDepartementJpaRepository;
    private final SpringRegionJpaRepository springRegionJpaRepository;
    private final SpringMandatJpaRepository springMandatJpaRepository;
    private final SpringSyndicatJpaRepository springSyndicatJpaRepository;
    private final SpringCadastreJpaRepository springCadastreJpaRepository;
    private final SpringInformationsCadastralesJpaRepository springInformationsCadastralesJpaRepository;

    public JpaCoproprieteRepository(SpringCoproprieteRepository springCoproprieteRepository, SpringVilleJpaRepository springVilleJpaRepository, SpringDepartementJpaRepository springDepartementJpaRepository, SpringRegionJpaRepository springRegionJpaRepository, SpringMandatJpaRepository springMandatJpaRepository, SpringSyndicatJpaRepository springSyndicatJpaRepository, SpringCadastreJpaRepository springCadastreJpaRepository, SpringInformationsCadastralesJpaRepository springInformationsCadastralesJpaRepository) {
        this.springCoproprieteRepository = springCoproprieteRepository;
        this.springVilleJpaRepository = springVilleJpaRepository;
        this.springDepartementJpaRepository = springDepartementJpaRepository;
        this.springRegionJpaRepository = springRegionJpaRepository;
        this.springMandatJpaRepository = springMandatJpaRepository;
        this.springSyndicatJpaRepository = springSyndicatJpaRepository;
        this.springCadastreJpaRepository = springCadastreJpaRepository;
        this.springInformationsCadastralesJpaRepository = springInformationsCadastralesJpaRepository;
    }

    @Override
    public void saveAll(List<Copropriete> coproprietes) {
        coproprietes.forEach(this::createOrUpdate);
    }

    private void createOrUpdate(Copropriete copropriete) {
        CoproprieteJpaEntity coproprieteJpaEntity = springCoproprieteRepository.findByNumeroImmatriculation(copropriete.numeroImmatriculation()).orElse(CoproprieteMapper.convert(copropriete));
        VilleJpaEntity villeJpaEntity = springVilleJpaRepository.findByCodeOfficielAndCodeOfficielArrondissement(copropriete.adresse().ville().codeOfficiel(), copropriete.adresse().ville().codeOfficielArrondissement()).orElseGet(() -> persistVille(copropriete.adresse().ville()));
        coproprieteJpaEntity.setVille(villeJpaEntity);
        createOrUpdateCadastre(copropriete.cadastre(), coproprieteJpaEntity);
        coproprieteJpaEntity = springCoproprieteRepository.save(coproprieteJpaEntity);

        createOrUpdateMandat(copropriete.mandat(), coproprieteJpaEntity);
    }

    private void createOrUpdateCadastre(Cadastre cadastre, CoproprieteJpaEntity coproprieteJpaEntity) {
        CadastreJpaEntity cadastreJpaEntity = coproprieteJpaEntity.getCadastre();
        if (cadastreJpaEntity == null) {
            cadastreJpaEntity = new CadastreJpaEntity();
        }
        cadastreJpaEntity.setNombreParcelles(cadastre.nombreParcelles());
        cadastreJpaEntity = springCadastreJpaRepository.save(cadastreJpaEntity);
        coproprieteJpaEntity.setCadastre(cadastreJpaEntity);
        createOrUpdateInformationsCadastrales(cadastreJpaEntity);

    }

    private void createOrUpdateInformationsCadastrales(CadastreJpaEntity cadastreJpaEntity) {
        cadastreJpaEntity.getInformationsCadastrales().forEach(info -> {
            springInformationsCadastralesJpaRepository.findByReference(info.getReference()).ifPresent(i -> info.setId(i.getId()));
            springInformationsCadastralesJpaRepository.save(info);
        });
    }

    private void createOrUpdateMandat(Mandat mandat, CoproprieteJpaEntity coproprieteJpaEntity) {
        if (mandat != null && mandat.syndicat() != null) {
            MandatJpaEntity mandatJpaEntity = MandatMapper.convert(mandat);
            mandatJpaEntity.setCopropriete(coproprieteJpaEntity);
            SyndicatJpaEntity syndicatJpaEntity =
                    springSyndicatJpaRepository.findBySiret(mandatJpaEntity.getSyndicat().getSiret()).orElseGet(mandatJpaEntity::getSyndicat);
            springSyndicatJpaRepository.save(syndicatJpaEntity);

            mandatJpaEntity.setSyndicat(syndicatJpaEntity);
            mandatJpaEntity = springMandatJpaRepository.save(mandatJpaEntity);
            if (coproprieteJpaEntity.getMandats() == null) {
                coproprieteJpaEntity.setMandats(new ArrayList<>());
            }
            coproprieteJpaEntity.getMandats().add(mandatJpaEntity);
        }
    }

    private VilleJpaEntity persistVille(Ville ville) {
        VilleJpaEntity villeJpaEntity = new VilleJpaEntity(ville);
        RegionJpaEntity regionJpaEntity = springRegionJpaRepository.save(villeJpaEntity.getDepartement().getRegion());
        villeJpaEntity.getDepartement().setRegion(regionJpaEntity);
        DepartementJpaEntity departementJpaEntity = springDepartementJpaRepository.save(villeJpaEntity.getDepartement());
        villeJpaEntity.setDepartement(departementJpaEntity);
        return springVilleJpaRepository.save(villeJpaEntity);
    }
}