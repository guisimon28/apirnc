package com.amperus.prospection.adapters.secondary.repositories;

import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteGeoLocalisation;

public class InMemoriesCoproprieteGeoLocalisation implements CoproprieteGeoLocalisation {

    private final String numeroImmatriculation;
    private final double latitude;
    private final double longitude;

    private InMemoriesCoproprieteGeoLocalisation(Builder builder) {
        this.numeroImmatriculation = builder.numeroImmatriculation;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    @Override
    public String getNumeroImmatriculation() {
        return numeroImmatriculation;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    public static class Builder {
        private String numeroImmatriculation;
        private double latitude;
        private double longitude;

        public Builder numeroImmatriculation(String numeroImmatriculation) {
            this.numeroImmatriculation = numeroImmatriculation;
            return this;
        }

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public InMemoriesCoproprieteGeoLocalisation build() {
            return new InMemoriesCoproprieteGeoLocalisation(this);
        }
    }
}