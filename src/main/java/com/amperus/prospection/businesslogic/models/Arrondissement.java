package com.amperus.prospection.businesslogic.models;

public record Arrondissement(String codePostal, String nom) {
    public static class Builder {
        private String codePostal;
        private String nom;

        public Builder codePostal(String codePostal) {
            this.codePostal = codePostal;
            return this;
        }

        public Builder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public Arrondissement build() {
            return new Arrondissement(codePostal, nom);
        }
    }
}