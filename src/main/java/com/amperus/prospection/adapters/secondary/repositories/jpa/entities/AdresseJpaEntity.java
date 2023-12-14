package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.Adresse;
import jakarta.persistence.Embeddable;

@Embeddable
public class AdresseJpaEntity {
    private String numeroEtVoie;
    private double latitude;
    private double longitude;

    public String getNumeroEtVoie() {
        return numeroEtVoie;
    }

    public void setNumeroEtVoie(String numeroEtVoie) {
        this.numeroEtVoie = numeroEtVoie;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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