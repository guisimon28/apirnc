package com.amperus.prospection.businesslogic.models;

import java.util.Arrays;
import java.util.Optional;

public enum MandatStatut {
    MANDAT_EN_COURS("Mandat en cours"),
    PAS_DE_MANDAT_EN_COURS("Pas de mandat en cours"),
    MANDAT_EXPIRE_SANS_SUCCESSEUR_DECLARE("Mandat expiré sans successeur déclaré"),
    MANDAT_EXPIRE_AVEC_SUCCESSEUR_DECLARE("Mandat expiré avec successeur déclaré");

    private final String label;

    MandatStatut(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Optional<MandatStatut> findFromLabel(String labelToFind) {
        return Arrays.stream(MandatStatut.values())
                .filter(statut -> statut.getLabel().equalsIgnoreCase(labelToFind))
                .findFirst();
    }
}