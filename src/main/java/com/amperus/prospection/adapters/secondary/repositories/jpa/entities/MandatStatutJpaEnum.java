package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.MandatStatut;

public enum MandatStatutJpaEnum {
    MANDAT_EN_COURS,
    PAS_DE_MANDAT_EN_COURS,
    MANDAT_EXPIRE_SANS_SUCCESSEUR_DECLARE,
    MANDAT_EXPIRE_AVEC_SUCCESSEUR_DECLARE;

    public static MandatStatutJpaEnum convertFromModel(MandatStatut statut) {
        return switch (statut) {
            case null -> null;
            case MandatStatut.MANDAT_EN_COURS -> MandatStatutJpaEnum.MANDAT_EN_COURS;
            case MandatStatut.MANDAT_EXPIRE_SANS_SUCCESSEUR_DECLARE -> MandatStatutJpaEnum.MANDAT_EXPIRE_SANS_SUCCESSEUR_DECLARE;
            case MandatStatut.MANDAT_EXPIRE_AVEC_SUCCESSEUR_DECLARE -> MandatStatutJpaEnum.MANDAT_EXPIRE_AVEC_SUCCESSEUR_DECLARE;
            default -> MandatStatutJpaEnum.PAS_DE_MANDAT_EN_COURS;
        };
    }
}
