package com.amperus.prospection.businesslogic.models;

import java.util.Arrays;
import java.util.Optional;

public enum PeriodeConstructionRange {

    AVANT_1949("Avant 1949"),
    DE_1949_A_1960("De 1949 à 1960"),
    DE_1961_A_1974("De 1961 à 1974"),
    DE_1975_A_1993("De 1975 à 1993"),
    DE_1994_A_2000("De 1994 à 2000"),
    DE_2001_A_2010("De 2001 à 2010"),
    A_COMPTER_2011("A compter de 2011"),
    INCONNUE("Non connue");

    private final String label;

    PeriodeConstructionRange(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Optional<PeriodeConstructionRange> findFromLabel(String labelToFind) {
        return Arrays.stream(PeriodeConstructionRange.values())
                .filter(periode -> periode.getLabel().equalsIgnoreCase(labelToFind))
                .findFirst();
    }
}