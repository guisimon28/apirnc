package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.DepartementJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDepartementJpaRepository extends JpaRepository<DepartementJpaEntity, UUID> {
    Optional<DepartementJpaEntity> findByCodeOfficiel(String codeOfficiel);
}