package com.amperus.prospection.adapters.secondary.repositories.jpa.mappers;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.AdresseJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.DepartementJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.RegionJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.VilleJpaEntity;
import com.amperus.prospection.businesslogic.models.Adresse;
import com.amperus.prospection.businesslogic.models.Departement;
import com.amperus.prospection.businesslogic.models.Region;
import com.amperus.prospection.businesslogic.models.Ville;

public class AdresseMapper {

    private AdresseMapper() {
    }

    public static AdresseJpaEntity convert(Adresse adresse) {
        if (adresse == null) {
            return null;
        }
        AdresseJpaEntity adresseJpaEntity = new AdresseJpaEntity();
        adresseJpaEntity.setNumeroEtVoie(adresse.numeroEtVoie());
        if (adresse.coordonneesGeographiques() != null) {
            adresseJpaEntity.setLatitude(adresse.coordonneesGeographiques().latitude());
            adresseJpaEntity.setLongitude(adresse.coordonneesGeographiques().longitude());
        }
        return adresseJpaEntity;
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

    public static DepartementJpaEntity convert(Departement departement) {
        if (departement == null) {
            return null;
        }
        DepartementJpaEntity departementJpaEntity = new DepartementJpaEntity();
        departementJpaEntity.setCodeOfficiel(departement.codeOfficiel());
        departementJpaEntity.setNomOfficiel(departement.nomOfficiel());
        departementJpaEntity.setRegion(AdresseMapper.convert(departement.region()));
        return departementJpaEntity;
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
        villeJpaEntity.setDepartement(AdresseMapper.convert(ville.departement()));
        return villeJpaEntity;
    }
}