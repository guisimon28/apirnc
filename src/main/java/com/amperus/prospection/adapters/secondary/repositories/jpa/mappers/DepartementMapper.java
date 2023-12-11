package com.amperus.prospection.adapters.secondary.repositories.jpa.mappers;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.DepartementJpaEntity;
import com.amperus.prospection.businesslogic.models.Departement;

public class DepartementMapper {

    private DepartementMapper() {
    }

    public static DepartementJpaEntity convert(Departement departement) {
        if (departement == null) {
            return null;
        }
        DepartementJpaEntity departementJpaEntity = new DepartementJpaEntity();
        departementJpaEntity.setCodeOfficiel(departement.codeOfficiel());
        departementJpaEntity.setNomOfficiel(departement.nomOfficiel());
        departementJpaEntity.setRegion(RegionMapper.convert(departement.region()));
        return departementJpaEntity;
    }
}
