package com.amperus.prospection.businesslogic.gateways.banprovision;

public record Geolocalisation(double latitude, double longitude) {
    public static class Builder {
        private double latitude;
        private double longitude;

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Geolocalisation build() {
            return new Geolocalisation(latitude, longitude);
        }
    }
}