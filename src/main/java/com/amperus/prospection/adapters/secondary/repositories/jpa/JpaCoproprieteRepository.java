package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.*;
import com.amperus.prospection.adapters.secondary.repositories.jpa.mappers.CoproprieteMapper;
import com.amperus.prospection.adapters.secondary.repositories.jpa.mappers.MandatMapper;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Copropriete;
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

    public JpaCoproprieteRepository(SpringCoproprieteRepository springCoproprieteRepository, SpringVilleJpaRepository springVilleJpaRepository, SpringDepartementJpaRepository springDepartementJpaRepository, SpringRegionJpaRepository springRegionJpaRepository, SpringMandatJpaRepository springMandatJpaRepository, SpringSyndicatJpaRepository springSyndicatJpaRepository) {
        this.springCoproprieteRepository = springCoproprieteRepository;
        this.springVilleJpaRepository = springVilleJpaRepository;
        this.springDepartementJpaRepository = springDepartementJpaRepository;
        this.springRegionJpaRepository = springRegionJpaRepository;
        this.springMandatJpaRepository = springMandatJpaRepository;
        this.springSyndicatJpaRepository = springSyndicatJpaRepository;
    }

    @Override
    public void saveAll(List<Copropriete> coproprietes) {
        coproprietes.forEach(this::createOrUpdate);
    }

    private void createOrUpdate(Copropriete copropriete) {
        CoproprieteJpaEntity coproprieteJpaEntity = springCoproprieteRepository.findByNumeroImmatriculation(copropriete.numeroImmatriculation()).orElse(CoproprieteMapper.convert(copropriete));
        VilleJpaEntity villeJpaEntity = springVilleJpaRepository.findByCodeOfficielAndCodeOfficielArrondissement(copropriete.adresse().ville().codeOfficiel(), copropriete.adresse().ville().codeOfficielArrondissement()).orElseGet(() -> persistVille(copropriete.adresse().ville()));
        coproprieteJpaEntity.setVille(villeJpaEntity);
        coproprieteJpaEntity = springCoproprieteRepository.save(coproprieteJpaEntity);

        if (copropriete.mandat() != null && copropriete.mandat().syndicat() != null) {
            MandatJpaEntity mandatJpaEntity = MandatMapper.convert(copropriete.mandat());
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