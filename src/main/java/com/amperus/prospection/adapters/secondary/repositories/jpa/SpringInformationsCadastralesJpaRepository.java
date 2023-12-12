package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.InformationCadastraleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringInformationsCadastralesJpaRepository extends JpaRepository<InformationCadastraleJpaEntity, UUID> {
    Optional<InformationCadastraleJpaEntity> findByReference(String reference);
}