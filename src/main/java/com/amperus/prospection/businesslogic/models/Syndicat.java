package com.amperus.prospection.businesslogic.models;

import org.apache.commons.text.WordUtils;

public record Syndicat(
        String raisonSociale,
        String siret,
        TypeSyndicat type,
        boolean cooperatif,
        String codeAPE,
        String commune) {

    public static class Builder {
        private String raisonSociale;
        private String siret;
        private TypeSyndicat type;
        private boolean cooperatif;
        private String codeAPE;
        private String commune;

        public Builder raisonSociale(String raisonSociale) {
            this.raisonSociale = raisonSociale;
            return this;
        }

        public Builder siret(String siret) {
            this.siret = siret;
            return this;
        }

        public Builder type(TypeSyndicat type) {
            this.type = type;
            return this;
        }

        public Builder cooperatif(boolean cooperatif) {
            this.cooperatif = cooperatif;
            return this;
        }

        public Builder codeApe(String codeApe) {
            this.codeAPE = codeApe;
            return this;
        }

        public Builder commune(String commune) {
            this.commune = commune;
            return this;
        }

        public void reFormatRaisonSociale() {
            if (siret != null && raisonSociale != null && raisonSociale.contains(siret)) {
                raisonSociale = raisonSociale.replace(siret, "");
                raisonSociale = WordUtils.capitalize(raisonSociale.toLowerCase().trim());
            }
        }

        public void reFormatCommune() {
            if (commune != null) {
                commune = WordUtils.capitalize(commune.toLowerCase().trim());
            }
        }

        public Syndicat build() {
            reFormatRaisonSociale();
            reFormatCommune();
            return new Syndicat(raisonSociale, siret, type, cooperatif, codeAPE, commune);
        }
    }
}