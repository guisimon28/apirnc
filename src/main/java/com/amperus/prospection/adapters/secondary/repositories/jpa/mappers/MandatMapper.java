package com.amperus.prospection.adapters.secondary.repositories.jpa.mappers;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.MandatJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.MandatStatutJpaEnum;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.SyndicatJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.TypeSyndicatJpaEnum;
import com.amperus.prospection.businesslogic.models.Mandat;
import com.amperus.prospection.businesslogic.models.MandatStatut;
import com.amperus.prospection.businesslogic.models.Syndicat;
import com.amperus.prospection.businesslogic.models.TypeSyndicat;

public class MandatMapper {

    private MandatMapper() {
    }

    public static MandatJpaEntity convert(Mandat mandat) {
        if (mandat == null) {
            return null;
        }
        MandatJpaEntity mandatJpaEntity = new MandatJpaEntity();
        mandatJpaEntity.setStatut(convertFromModel(mandat.statut()));
        mandatJpaEntity.setSyndicat(convert(mandat.syndicat()));
        return mandatJpaEntity;
    }

    public static SyndicatJpaEntity convert(Syndicat syndicat) {
        if (syndicat == null) {
            return null;
        }
        SyndicatJpaEntity syndicatJpaEntity = new SyndicatJpaEntity();
        if (syndicat.representantLegal() != null) {
            syndicatJpaEntity.setNom(syndicat.representantLegal().nom());
            syndicatJpaEntity.setSiret(syndicat.representantLegal().siret());
            syndicatJpaEntity.setCodeAPE(syndicat.representantLegal().codeAPE());
        }
        syndicatJpaEntity.setCooperatif(syndicat.cooperatif());
        syndicatJpaEntity.setNombreUnionsSyndicats(syndicat.nombreUnionsSyndicats());
        syndicatJpaEntity.setNombreAssociationFonciereUrbaineLibre(syndicat.nombreAssociationFonciereUrbaineLibre());
        syndicatJpaEntity.setNombreAssociationSyndicaleLibre(syndicat.nombreAssociationSyndicaleLibre());
        syndicatJpaEntity.setType(convertFromModel(syndicat.type()));
        return syndicatJpaEntity;
    }

    private static TypeSyndicatJpaEnum convertFromModel(TypeSyndicat type) {
        return switch (type) {
            case TypeSyndicat.BENEVOLE -> TypeSyndicatJpaEnum.BENEVOLE;
            case TypeSyndicat.PROFESSIONNEL -> TypeSyndicatJpaEnum.PROFESSIONNEL;
            default -> TypeSyndicatJpaEnum.INCONNU;
        };
    }

    private static MandatStatutJpaEnum convertFromModel(MandatStatut statut) {
        return switch (statut) {
            case MandatStatut.MANDAT_EN_COURS -> MandatStatutJpaEnum.MANDAT_EN_COURS;
            case MandatStatut.MANDAT_EXPIRE_SANS_SUCCESSEUR_DECLARE -> MandatStatutJpaEnum.MANDAT_EXPIRE_SANS_SUCCESSEUR_DECLARE;
            case MandatStatut.MANDAT_EXPIRE_AVEC_SUCCESSEUR_DECLARE -> MandatStatutJpaEnum.MANDAT_EXPIRE_AVEC_SUCCESSEUR_DECLARE;
            default -> MandatStatutJpaEnum.PAS_DE_MANDAT_EN_COURS;
        };
    }
}
