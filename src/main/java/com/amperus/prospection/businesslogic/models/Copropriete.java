package com.amperus.prospection.businesslogic.models;

public record Copropriete(
        String numeroImmatriculation,
        String nomUsage,
        EtablissementPublicCooperationIntercommunale epci,
        Syndicat syndicat,
        Lots lots,
        Cadastre cadastre,
        QuartierPrioritaire quartierPrioritaire,
        Adresse adresse,
        Caracteristique caracteristique,
        Arretes arretes) {

    public static class Builder {
        private String numeroImmatriculation;
        private String nomUsage;
        private EtablissementPublicCooperationIntercommunale epci;
        private Syndicat syndicat;
        private Lots lots;
        private Cadastre cadastre;
        private QuartierPrioritaire quartierPrioritaire;
        private Adresse adresse;
        private Caracteristique caracteristique;
        private Arretes arretes;

        public Builder numeroImmatriculation(String numeroImmatriculation) {
            this.numeroImmatriculation = numeroImmatriculation;
            return this;
        }

        public Builder nomUsage(String nomUsage) {
            this.nomUsage = nomUsage;
            return this;
        }

        public Builder epci(EtablissementPublicCooperationIntercommunale epci) {
            this.epci = epci;
            return this;
        }

        public Builder syndicat(Syndicat syndicat) {
            this.syndicat = syndicat;
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

        public Builder quartierPrioritaire(QuartierPrioritaire quartierPrioritaire) {
            this.quartierPrioritaire = quartierPrioritaire;
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

        public Builder arretes(Arretes arretes) {
            this.arretes = arretes;
            return this;
        }

        public Copropriete build() {
            return new Copropriete(
                    numeroImmatriculation, nomUsage, epci, syndicat, lots, cadastre, quartierPrioritaire, adresse, caracteristique, arretes);
        }
    }
}