package com.amperus.prospection.businesslogic.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import java.util.List;

public record Copropriete(
        String numeroImmatriculation,
        String nomUsage,
        Mandat mandat,
        Lots lots,
        List<InformationCadastrale> informationsCadastrales,
        Adresse adresse,
        Caracteristique caracteristique) {

    public static class Builder {
        private String numeroImmatriculation;
        private String nomUsage;
        private Mandat mandat;
        private Lots lots;
        private List<InformationCadastrale> informationsCadastrales;
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

        public Builder informationsCadastrales(List<InformationCadastrale> informationsCadastrales) {
            this.informationsCadastrales = informationsCadastrales;
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

        public void reFormatNomUsage() {
            if (StringUtils.isNotBlank(nomUsage)) {
                nomUsage = WordUtils.capitalize(nomUsage.toLowerCase().trim());
            }
        }

        public Copropriete build() {
            reFormatNomUsage();
            return new Copropriete(
                    numeroImmatriculation, nomUsage, mandat, lots, informationsCadastrales, adresse, caracteristique);
        }
    }
}