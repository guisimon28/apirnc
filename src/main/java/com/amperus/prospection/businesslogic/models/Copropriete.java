package com.amperus.prospection.businesslogic.models;

public record Copropriete(
        String numeroImmatriculation,
        String nomUsage,
        Mandat mandat,
        Lots lots,
        Cadastre cadastre,
        Adresse adresse,
        Caracteristique caracteristique) {

    public static class Builder {
        private String numeroImmatriculation;
        private String nomUsage;
        private Mandat mandat;
        private Lots lots;
        private Cadastre cadastre;
        private Adresse adresse;
        private Caracteristique caracteristique;

        public Builder numeroImmatriculation(String numeroImmatriculation) {
            this.numeroImmatriculation = numeroImmatriculation;
            return this;
        }

        public Builder nomUsage(String nomUsage) {
            this.nomUsage = nomUsage;
            return this;
        }

        public Builder mandat(Mandat mandat) {
            this.mandat = mandat;
            return this;
        }

        public Builder lots(Lots lots) {
            this.lots = lots;
            return this;
        }

        public Builder cadastre(Cadastre cadastre) {
            this.cadastre = cadastre;
            return this;
        }

        public Builder adresse(Adresse adresse) {
            this.adresse = adresse;
            return this;
        }

        public Builder caracteristique(Caracteristique caracteristique) {
            this.caracteristique = caracteristique;
            return this;
        }

        public Copropriete build() {
            return new Copropriete(
                    numeroImmatriculation, nomUsage, mandat, lots, cadastre, adresse, caracteristique);
        }
    }
}