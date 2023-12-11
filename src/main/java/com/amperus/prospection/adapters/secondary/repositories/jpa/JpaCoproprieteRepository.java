package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.CoproprieteJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.DepartementJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.RegionJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.VilleJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.mappers.CoproprieteMapper;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.Ville;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaCoproprieteRepository implements CoproprieteRepository {

    private final SpringCoproprieteRepository springCoproprieteRepository;
    private final SpringVilleJpaRepository springVilleJpaRepository;
    private final SpringDepartementJpaRepository springDepartementJpaRepository;
    private final SpringRegionJpaRepository springRegionJpaRepository;

    public JpaCoproprieteRepository(SpringCoproprieteRepository springCoproprieteRepository, SpringVilleJpaRepository springVilleJpaRepository, SpringDepartementJpaRepository springDepartementJpaRepository, SpringRegionJpaRepository springRegionJpaRepository) {
        this.springCoproprieteRepository = springCoproprieteRepository;
        this.springVilleJpaRepository = springVilleJpaRepository;
        this.springDepartementJpaRepository = springDepartementJpaRepository;
        this.springRegionJpaRepository = springRegionJpaRepository;
    }

    @Override
    public void saveAll(List<Copropriete> coproprietes) {
        coproprietes.forEach(this::createOrUpdate);
    }

    private void createOrUpdate(Copropriete copropriete) {
        CoproprieteJpaEntity coproprieteJpaEntity = springCoproprieteRepository.findByNumeroImmatriculation(copropriete.numeroImmatriculation()).orElse(CoproprieteMapper.convert(copropriete));
        VilleJpaEntity villeJpaEntity = springVilleJpaRepository.findByCodeOfficielAndCodeOfficielArrondissement(copropriete.adresse().ville().codeOfficiel(), copropriete.adresse().ville().codeOfficielArrondissement()).orElseGet(() -> persistVille(copropriete.adresse().ville()));
        coproprieteJpaEntity.setVille(villeJpaEntity);
        springCoproprieteRepository.save(coproprieteJpaEntity);
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