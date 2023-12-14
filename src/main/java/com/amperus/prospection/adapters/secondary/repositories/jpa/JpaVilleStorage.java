package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.DepartementJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.RegionJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.VilleJpaEntity;
import com.amperus.prospection.businesslogic.models.Ville;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaVilleStorage {

    private final SpringVilleJpaRepository springVilleJpaRepository;
    private final SpringDepartementJpaRepository springDepartementJpaRepository;
    private final SpringRegionJpaRepository springRegionJpaRepository;

    public JpaVilleStorage(SpringVilleJpaRepository springVilleJpaRepository,
                           SpringDepartementJpaRepository springDepartementJpaRepository, SpringRegionJpaRepository springRegionJpaRepository) {
        this.springVilleJpaRepository = springVilleJpaRepository;
        this.springDepartementJpaRepository = springDepartementJpaRepository;
        this.springRegionJpaRepository = springRegionJpaRepository;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveAll(List<Ville> villes) {
        villes.forEach(this::createOrUpdate);
    }

    private void createOrUpdate(Ville ville) {
        Optional<VilleJpaEntity> villeJpaEntity = springVilleJpaRepository.findByCodeOfficielAndCodeOfficielArrondissementAndNomOfficiel(ville.codeOfficiel(),
                ville.codeOfficielArrondissement(), ville.nomOfficiel());
        if (villeJpaEntity.isEmpty()) {
            createNewVille(ville);
        }
    }

    private void createNewVille(Ville ville) {
        VilleJpaEntity villeJpaEntity = new VilleJpaEntity(ville);
        RegionJpaEntity regionJpaEntity = springRegionJpaRepository.save(villeJpaEntity.getDepartement().getRegion());
        villeJpaEntity.getDepartement().setRegion(regionJpaEntity);
        DepartementJpaEntity departementJpaEntity = springDepartementJpaRepository.save(villeJpaEntity.getDepartement());
        villeJpaEntity.setDepartement(departementJpaEntity);
        springVilleJpaRepository.save(villeJpaEntity);
    }

    public Optional<VilleJpaEntity> find(Ville ville) {
        return springVilleJpaRepository.findByCodeOfficielAndCodeOfficielArrondissementAndNomOfficiel(ville.codeOfficiel(),
                ville.codeOfficielArrondissement(), ville.nomOfficiel());
    }

}