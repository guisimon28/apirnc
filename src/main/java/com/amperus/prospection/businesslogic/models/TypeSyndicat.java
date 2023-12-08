package com.amperus.prospection.businesslogic.models;

import java.util.Arrays;
import java.util.Optional;

public enum TypeSyndicat {
    BENEVOLE("bénévole"),
    PROFESSIONNEL("professionnel"),
    INCONNU("non connu");

    private final String label;

    TypeSyndicat(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Optional<TypeSyndicat> findFromLabel(String labelToFind) {
        return Arrays.stream(TypeSyndicat.values())
                .filter(type -> type.getLabel().equalsIgnoreCase(labelToFind))
                .findFirst();
    }
}