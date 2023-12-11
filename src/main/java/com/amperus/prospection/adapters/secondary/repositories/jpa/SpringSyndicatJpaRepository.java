package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.SyndicatJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringSyndicatJpaRepository extends JpaRepository<SyndicatJpaEntity, UUID> {
    Optional<SyndicatJpaEntity> findBySiret(String siret);
}