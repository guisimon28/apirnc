package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.RegionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringRegionJpaRepository extends JpaRepository<RegionJpaEntity, Long> {
}