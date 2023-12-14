package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.DepartementJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.RegionJpaEntity;
import com.amperus.prospection.businesslogic.models.Departement;
import com.amperus.prospection.businesslogic.models.Ville;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class JpaDepartementStorage {

    private final SpringDepartementJpaRepository springDepartementJpaRepository;

    private final JpaRegionStorage jpaRegionStorage;

    public JpaDepartementStorage(SpringDepartementJpaRepository springDepartementJpaRepository, JpaRegionStorage jpaRegionStorage) {
        this.springDepartementJpaRepository = springDepartementJpaRepository;
        this.jpaRegionStorage = jpaRegionStorage;
    }

    public List<DepartementJpaEntity> updateAndGetAllDepartements(List<Ville> villes) {
        List<Departement> departements = villes.stream().map(Ville::departement).filter(Objects::nonNull).toList();
        List<RegionJpaEntity> regionJpaEntities = jpaRegionStorage.updateAndGetAllRegions(departements);
        List<DepartementJpaEntity> departementJpaEntities = springDepartementJpaRepository.findAll();
        return createOrUpdate(departements, departementJpaEntities, regionJpaEntities);
    }

    private List<DepartementJpaEntity> createOrUpdate(List<Departement> departements, List<DepartementJpaEntity> departementJpaEntities,
                                                      List<RegionJpaEntity> regionJpaEntities) {
        departements.forEach(departement -> createOrUpdate(departement, departementJpaEntities, regionJpaEntities));
        return springDepartementJpaRepository.saveAll(departementJpaEntities);
    }

    private void createOrUpdate(Departement departement, List<DepartementJpaEntity> departementJpaEntities, List<RegionJpaEntity> regionJpaEntities) {
        DepartementJpaEntity departementJpaEntity =
                departementJpaEntities.stream().filter(d -> d.isSame(departement))
                        .findFirst().orElseGet(() -> {
                            var newDepartement = new DepartementJpaEntity();
                            departementJpaEntities.add(newDepartement);
                            if (departement.region() != null) {
                                regionJpaEntities.stream().filter((r -> r.isSame(departement.region())))
                                        .findFirst().ifPresent(newDepartement::setRegion);
                            }
                            return newDepartement;
                        });
        departementJpaEntity.update(departement);
    }

}