package com.amperus.prospection.adapters.secondary.rncprovision;

enum CsvHeader {
    NUMERO_IMMATRICULATION("Numéro d'immatriculation"),
    NOM_USAGE("Nom d’usage de la copropriété"),
    REFERENCE_CADASTRALE("Référence Cadastrale "),
    CODE_INSEE_COMMUNE("Code INSEE commune "),
    PREFIXE("Préfixe "),
    SECTION("Section "),
    NUMERO_PARCELLE("Numéro parcelle "),
    NOMBRE_TOTAL_LOTS("Nombre total de lots"),
    NOMBRE_LOTS_STATIONNEMENT("Nombre de lots de stationnement"),
    NOMBRE_LOTS_HABITATION("Nombre de lots à usage d’habitation"),
    NOMBRE_LOTS_HABITATION_BUREAUX_COMMERCES("Nombre total de lots à usage d’habitation, de bureaux ou de commerces"),
    MANDAT_EN_COURS("Mandat en cours dans la copropriété"),
    SIRET_REPRESENTANT("Siret représentant légal (si existe)"),
    IDENTIFICATION_REPRESENTANT("Identification du représentant légal  (raison sociale et le numéro SIRET du syndic professionnel ou Civilité/prénom/ nom " +
            "du syndic bénévole ou coopératif)"),
    CODE_APE("Code APE"),
    COMMUNE_REPRESENTANT("Commune du représentant légal"),
    TYPE_SYNDIC("Type de syndic : bénévole / professionnel / non connu"),
    SYNDICAT_COOPERATIF("Syndicat coopératif"),
    DATE_REGLEMENT("Date du règlement de copropriété"),
    RESIDENCE_SERVICE("Résidence service"),
    COPRO_AIDEE("Copro aidée"),
    COPRO_ACV("Copro dans ACV"),
    COPRO_PVD("Copro dans PVD"),
    PERIODE_CONSTRUCTION("Période de construction"),
    NUMERO_ET_VOIE("Numéro et Voie (adresse de référence)"),
    CODE_POSTAL("Code postal (adresse de référence)"),
    LATITUDE("lat"),
    LONGITUDE("long"),
    CODE_OFFICIEL_COMMUNE("Code Officiel Commune"),
    NOM_OFFICIEL_COMMUNE("Nom Officiel Commune"),
    CODE_OFFICIEL_ARRONDISSEMENT("Code Officiel Arrondissement Commune"),
    NOM_OFFICIEL_ARRONDISSEMENT("Nom Officiel Arrondissement Commune"),
    CODE_OFFICIEL_DEPARTEMENT("Code Officiel Département"),
    NOM_OFFICIEL_DEPARTEMENT("Nom Officiel Département"),
    CODE_OFFICIEL_REGION("Code Officiel Région"),
    NOM_OFFICIEL_REGION("Nom Officiel Région");

    private final String label;

    CsvHeader(String label) {
        this.label = label;
    }

    CsvHeader() {
        this.label = name();
    }

    public String getLabel() {
        return label;
    }
}