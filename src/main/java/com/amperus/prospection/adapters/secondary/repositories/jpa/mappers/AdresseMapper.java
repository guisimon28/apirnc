package com.amperus.prospection.adapters.secondary.repositories.jpa.mappers;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.AdresseJpaEntity;
import com.amperus.prospection.businesslogic.models.Adresse;

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
}