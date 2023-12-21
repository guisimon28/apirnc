package com.amperus.prospection.businesslogic.models;

public record Departement(String code, String nom, Region region) {
    public static class Builder {
        private String code;
        private String nom;
        private Region region;

        public Builder code(String codeOfficiel) {
            this.code = codeOfficiel;
            return this;
        }

        public Builder nom(String nomOfficiel) {
            this.nom = nomOfficiel;
            return this;
        }

        public Builder region(Region region) {
            this.region = region;
            return this;
        }

        public Departement build() {
            return new Departement(code, nom, region);
        }
    }
}