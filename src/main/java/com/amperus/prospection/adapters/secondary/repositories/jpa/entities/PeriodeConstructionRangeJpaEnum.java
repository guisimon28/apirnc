package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.PeriodeConstructionRange;

public enum PeriodeConstructionRangeJpaEnum {
    AVANT_1949,
    DE_1949_A_1960,
    DE_1961_A_1974,
    DE_1975_A_1993,
    DE_1994_A_2000,
    DE_2001_A_2010,
    A_COMPTER_2011,
    INCONNUE;

    public static PeriodeConstructionRangeJpaEnum convertFromModel(PeriodeConstructionRange periodeConstructionRange) {
        return switch (periodeConstructionRange) {
            case null -> null;
            case PeriodeConstructionRange.AVANT_1949 -> PeriodeConstructionRangeJpaEnum.AVANT_1949;
            case PeriodeConstructionRange.DE_1949_A_1960 -> PeriodeConstructionRangeJpaEnum.DE_1949_A_1960;
            case PeriodeConstructionRange.DE_1961_A_1974 -> PeriodeConstructionRangeJpaEnum.DE_1961_A_1974;
            case PeriodeConstructionRange.DE_1975_A_1993 -> PeriodeConstructionRangeJpaEnum.DE_1975_A_1993;
            case PeriodeConstructionRange.DE_1994_A_2000 -> PeriodeConstructionRangeJpaEnum.DE_1994_A_2000;
            case PeriodeConstructionRange.DE_2001_A_2010 -> PeriodeConstructionRangeJpaEnum.DE_2001_A_2010;
            case PeriodeConstructionRange.A_COMPTER_2011 -> PeriodeConstructionRangeJpaEnum.A_COMPTER_2011;
            default -> PeriodeConstructionRangeJpaEnum.INCONNUE;
        };
    }
}