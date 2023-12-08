package com.amperus.prospection.businesslogic.models;

public record Mandat(MandatStatut statut, Syndicat syndicat) {
    public static class Builder {
        private MandatStatut statut;
        private Syndicat syndicat;

        public Builder statut(MandatStatut statut) {
            this.statut = statut;
            return this;
        }

        public Builder syndicat(Syndicat syndicat) {
            this.syndicat = syndicat;
            return this;
        }

        public Mandat build() {
            return new Mandat(statut, syndicat);
        }
    }

}