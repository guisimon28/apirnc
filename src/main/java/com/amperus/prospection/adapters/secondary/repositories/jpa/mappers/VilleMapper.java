package com.amperus.prospection.adapters.secondary.repositories.jpa.mappers;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.VilleJpaEntity;
import com.amperus.prospection.businesslogic.models.Ville;

public class VilleMapper {

    private VilleMapper() {
    }

    public static VilleJpaEntity convert(Ville ville) {
        if (ville == null) {
            return null;
        }
        VilleJpaEntity villeJpaEntity = new VilleJpaEntity();
        villeJpaEntity.setCodeOfficiel(ville.codeOfficiel());
        villeJpaEntity.setNomOfficiel(ville.nomOfficiel());
        villeJpaEntity.setCodeOfficielArrondissement(ville.codeOfficielArrondissement());
        villeJpaEntity.setNomOfficielArrondissement(ville.nomOfficielArrondissement());
        villeJpaEntity.setDepartement(DepartementMapper.convert(ville.departement()));
        return villeJpaEntity;
    }
}
