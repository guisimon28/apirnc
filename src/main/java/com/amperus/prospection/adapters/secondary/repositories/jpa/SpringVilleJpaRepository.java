package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.VilleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringVilleJpaRepository extends JpaRepository<VilleJpaEntity, UUID> {
    Optional<VilleJpaEntity> findByCodeOfficielAndCodeOfficielArrondissementAndNomOfficiel(String codeOfficiel, String codeOfficielArrondissement,
                                                                                           String nomOfficiel);
}