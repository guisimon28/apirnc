package com.amperus.prospection.adapters.secondary.repositories.jpa.mappers;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.RegionJpaEntity;
import com.amperus.prospection.businesslogic.models.Region;

public class RegionMapper {

    private RegionMapper() {
    }

    public static RegionJpaEntity convert(Region region) {
        if (region == null) {
            return null;
        }
        RegionJpaEntity regionJpaEntity = new RegionJpaEntity();
        regionJpaEntity.setCodeOfficiel(region.codeOfficiel());
        regionJpaEntity.setNomOfficiel(region.nomOfficiel());
        return regionJpaEntity;
    }
}
