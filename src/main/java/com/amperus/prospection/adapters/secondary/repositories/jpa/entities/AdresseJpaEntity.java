package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.Adresse;
import com.amperus.prospection.businesslogic.models.CoordonneesGeographiques;
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
        if (adresse != null) {
            this.numeroEtVoie = adresse.numeroEtVoie();
            if (adresse.coordonneesGeographiques() != null) {
                this.latitude = adresse.coordonneesGeographiques().latitude();
                this.longitude = adresse.coordonneesGeographiques().longitude();
            }
        }
    }

    void completeBuilder(Adresse.Builder builder) {
        builder.coordonneesGeographiques(new CoordonneesGeographiques.Builder().latitude(latitude).longitude(longitude).build())
                .numeroEtVoie(numeroEtVoie);
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