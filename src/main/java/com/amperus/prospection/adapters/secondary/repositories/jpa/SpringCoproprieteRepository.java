package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.CoproprieteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface SpringCoproprieteRepository extends JpaRepository<CoproprieteJpaEntity, UUID> {
    @Transactional(readOnly = true)
    Optional<CoproprieteJpaEntity> findByNumeroImmatriculation(String numeroImmatriculation);
}