package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.TypeSyndicat;

public enum TypeSyndicatJpaEnum {
    BENEVOLE,
    PROFESSIONNEL,
    INCONNU;

    public static TypeSyndicatJpaEnum convertFromModel(TypeSyndicat type) {
        return switch (type) {
            case null -> null;
            case TypeSyndicat.BENEVOLE -> TypeSyndicatJpaEnum.BENEVOLE;
            case TypeSyndicat.PROFESSIONNEL -> TypeSyndicatJpaEnum.PROFESSIONNEL;
            default -> TypeSyndicatJpaEnum.INCONNU;
        };
    }

    public static TypeSyndicat convertToDomain(TypeSyndicatJpaEnum type) {
        return switch (type) {
            case null -> null;
            case TypeSyndicatJpaEnum.BENEVOLE -> TypeSyndicat.BENEVOLE;
            case TypeSyndicatJpaEnum.PROFESSIONNEL -> TypeSyndicat.PROFESSIONNEL;
            default -> TypeSyndicat.INCONNU;
        };
    }
}
