package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.Adresse;
import jakarta.persistence.Embeddable;

@Embeddable
public class AdresseJpaEntity {
    private String numeroEtVoie;

    private String numeroEtVoieGpx;

    private double latitude;
    private double longitude;

    public String getNumeroEtVoie() {
        return numeroEtVoie;
    }

    public void setNumeroEtVoie(String numeroEtVoie) {
        this.numeroEtVoie = numeroEtVoie;
    }

    public String getNumeroEtVoieGpx() {
        return numeroEtVoieGpx;
    }

    public void setNumeroEtVoieGpx(String numeroEtVoieGpx) {
        this.numeroEtVoieGpx = numeroEtVoieGpx;
    }

    public void update(Adresse adresse) {
        this.numeroEtVoie = adresse.numeroEtVoie();
        if (adresse.coordonneesGeographiques() != null) {
            this.latitude = adresse.coordonneesGeographiques().latitude();
            this.longitude = adresse.coordonneesGeographiques().longitude();
        }
    }

    @Override
    public String toString() {
        return "AdresseJpaEntity{" +
                "numeroEtVoie='" + numeroEtVoie + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}