package com.amperus.prospection.adapters.secondary.repositories.jpa.mappers;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.CoproprieteJpaEntity;
import com.amperus.prospection.businesslogic.models.Copropriete;

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
        coproprieteJpaEntity.setVille(VilleMapper.convert(copropriete.adresse().ville()));
        return coproprieteJpaEntity;
    }
}
