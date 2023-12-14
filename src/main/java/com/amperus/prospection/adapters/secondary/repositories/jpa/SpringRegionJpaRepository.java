package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.RegionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringRegionJpaRepository extends JpaRepository<RegionJpaEntity, UUID> {
    Optional<RegionJpaEntity> findByCodeOfficiel(String codeOfficiel);
}