package com.amperus.prospection.businesslogic.models;

public record Ville(String codePostal, String nom, Arrondissement arrondissement, Departement departement) {
    public static class Builder {
        private String codePostal;
        private String nom;
        private Arrondissement arrondissement;
        private Departement departement;

        public Builder codePostal(String codePostal) {
            this.codePostal = codePostal;
            return this;
        }

        public Builder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public Builder arrondissement(Arrondissement arrondissement) {
            this.arrondissement = arrondissement;
            return this;
        }

        public Builder departement(Departement departement) {
            this.departement = departement;
            return this;
        }

        public Ville build() {
            return new Ville(codePostal, nom, arrondissement, departement);
        }
    }
}