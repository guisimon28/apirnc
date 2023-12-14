package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.RegionJpaEntity;
import com.amperus.prospection.businesslogic.models.Departement;
import com.amperus.prospection.businesslogic.models.Region;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class JpaRegionStorage {
    private final SpringRegionJpaRepository springRegionJpaRepository;

    public JpaRegionStorage(SpringRegionJpaRepository springRegionJpaRepository) {
        this.springRegionJpaRepository = springRegionJpaRepository;
    }

    public List<RegionJpaEntity> updateAndGetAllRegions(List<Departement> departements) {
        List<Region> regions = departements.stream().map(Departement::region).filter(Objects::nonNull).toList();
        List<RegionJpaEntity> regionJpaEntities = springRegionJpaRepository.findAll();
        return createOrUpdate(regions, regionJpaEntities);
    }

    private List<RegionJpaEntity> createOrUpdate(List<Region> regions, List<RegionJpaEntity> regionJpaEntities) {
        regions.forEach(region -> createOrUpdate(region, regionJpaEntities));
        return springRegionJpaRepository.saveAll(regionJpaEntities);
    }

    private void createOrUpdate(Region region, List<RegionJpaEntity> regionJpaEntities) {
        RegionJpaEntity regionJpaEntity = regionJpaEntities.stream().filter(r -> r.isSame(region))
                .findFirst().orElseGet(() -> {
                    var newRegion = new RegionJpaEntity();
                    regionJpaEntities.add(newRegion);
                    return newRegion;
                });
        regionJpaEntity.update(region);
    }

}