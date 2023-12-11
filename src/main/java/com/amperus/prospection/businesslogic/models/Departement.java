package com.amperus.prospection.businesslogic.models;

public record Departement(String codeOfficiel, String nomOfficiel, Region region) {
    public static class Builder {
        private String codeOfficiel;
        private String nomOfficiel;
        private Region region;

        public Builder codeOfficiel(String codeOfficiel) {
            this.codeOfficiel = codeOfficiel;
            return this;
        }

        public Builder nomOfficiel(String nomOfficiel) {
            this.nomOfficiel = nomOfficiel;
            return this;
        }

        public Builder region(Region region) {
            this.region = region;
            return this;
        }

        public Departement build() {
            return new Departement(codeOfficiel, nomOfficiel, region);
        }
    }
}