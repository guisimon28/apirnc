package com.amperus.prospection.businesslogic.models;

public record Syndicat(
        TypeSyndicat type,
        RepresentantLegal representantLegal,
        boolean cooperatif,
        int nombreAssociationSyndicaleLibre,
        int nombreAssociationFonciereUrbaineLibre,
        int nombreUnionsSyndicats) {

    public static class Builder {
        private TypeSyndicat type;
        private RepresentantLegal representantLegal;
        private boolean cooperatif;
        private int nombreAssociationSyndicaleLibre;
        private int nombreAssociationFonciereUrbaineLibre;
        private int nombreUnionsSyndicats;

        public Builder type(TypeSyndicat type) {
            this.type = type;
            return this;
        }

        public Builder representantLegal(RepresentantLegal representantLegal) {
            this.representantLegal = representantLegal;
            return this;
        }

        public Builder cooperatif(boolean cooperatif) {
            this.cooperatif = cooperatif;
            return this;
        }

        public Builder nombreAssociationSyndicaleLibre(int nombreAssociationSyndicaleLibre) {
            this.nombreAssociationSyndicaleLibre = nombreAssociationSyndicaleLibre;
            return this;
        }

        public Builder nombreAssociationFonciereUrbaineLibre(int nombreAssociationFonciereUrbaineLibre) {
            this.nombreAssociationFonciereUrbaineLibre = nombreAssociationFonciereUrbaineLibre;
            return this;
        }

        public Builder nombreUnionsSyndicats(int nombreUnionsSyndicats) {
            this.nombreUnionsSyndicats = nombreUnionsSyndicats;
            return this;
        }

        public Syndicat build() {
            return new Syndicat(
                    type, representantLegal, cooperatif, nombreAssociationSyndicaleLibre,
                    nombreAssociationFonciereUrbaineLibre, nombreUnionsSyndicats
            );
        }
    }
}