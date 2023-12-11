package com.amperus.prospection.businesslogic.models;

public record Ville(String codeOfficiel, String nomOfficiel, String codeOfficielArrondissement,
                    String nomOfficielArrondissement, Departement departement) {
    public static class Builder {
        private String codeOfficiel;
        private String nomOfficiel;
        private String codeOfficielArrondissement;
        private String nomOfficielArrondissement;
        private Departement departement;

        public Builder codeOfficiel(String codeOfficiel) {
            this.codeOfficiel = codeOfficiel;
            return this;
        }

        public Builder nomOfficiel(String nomOfficiel) {
            this.nomOfficiel = nomOfficiel;
            return this;
        }

        public Builder codeOfficielArrondissement(String codeOfficielArrondissement) {
            this.codeOfficielArrondissement = codeOfficielArrondissement;
            return this;
        }

        public Builder nomOfficielArrondissement(String nomOfficielArrondissement) {
            this.nomOfficielArrondissement = nomOfficielArrondissement;
            return this;
        }

        public Builder departement(Departement departement) {
            this.departement = departement;
            return this;
        }

        public Ville build() {
            return new Ville(codeOfficiel, nomOfficiel, codeOfficielArrondissement, nomOfficielArrondissement, departement);
        }
    }
}