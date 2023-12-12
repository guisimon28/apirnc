package com.amperus.prospection.adapters.secondary.repositories.jpa.mappers;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.CoproprieteJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.PeriodeConstructionRangeJpaEnum;
import com.amperus.prospection.businesslogic.models.Caracteristique;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.Lots;
import com.amperus.prospection.businesslogic.models.PeriodeConstructionRange;

public class CoproprieteMapper {

    private CoproprieteMapper() {
    }

    public static CoproprieteJpaEntity convert(Copropriete copropriete) {
        if (copropriete == null) {
            return null;
        }
        CoproprieteJpaEntity coproprieteJpaEntity = new CoproprieteJpaEntity();
        coproprieteJpaEntity.setNomUsage(copropriete.nomUsage());
        coproprieteJpaEntity.setNumeroImmatriculation(copropriete.numeroImmatriculation());
        coproprieteJpaEntity.setAdresseJpaEntity(AdresseMapper.convert(copropriete.adresse()));
        coproprieteJpaEntity.setVille(AdresseMapper.convert(copropriete.adresse().ville()));
        updateCaracteristique(coproprieteJpaEntity, copropriete.caracteristique());
        updateLots(coproprieteJpaEntity, copropriete.lots());
        coproprieteJpaEntity.setCadastre(CadastreMapper.convert(copropriete.cadastre()));
        return coproprieteJpaEntity;
    }


    private static void updateCaracteristique(CoproprieteJpaEntity coproprieteJpaEntity, Caracteristique caracteristique) {
        if (caracteristique == null) {
            return;
        }
        coproprieteJpaEntity.setDateReglement(caracteristique.dateReglement());
        coproprieteJpaEntity.setDansActionCoeurDeVille(caracteristique.dansActionCoeurDeVille());
        coproprieteJpaEntity.setAidee(caracteristique.aidee());
        coproprieteJpaEntity.setDansPetiteVilleDeDemain(caracteristique.dansPetiteVilleDeDemain());
        coproprieteJpaEntity.setResidenceService(caracteristique.residenceService());
        coproprieteJpaEntity.setPeriodeConstruction(convertFromModel(caracteristique.periodeConstruction()));
    }

    private static void updateLots(CoproprieteJpaEntity coproprieteJpaEntity, Lots lots) {
        if (lots == null) {
            return;
        }
        coproprieteJpaEntity.setNombreLotTotal(lots.nombreTotal());
        coproprieteJpaEntity.setNombreLotUsageHabitation(lots.nombreUsageHabitation());
        coproprieteJpaEntity.setNombreLotUsageHabitationBureauxCommerces(lots.nombreUsageHabitationBureauxCommerces());
        coproprieteJpaEntity.setNombreLotTotal(lots.nombreStationnement());
    }

    private static PeriodeConstructionRangeJpaEnum convertFromModel(PeriodeConstructionRange periodeConstructionRange) {
        return switch (periodeConstructionRange) {
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
