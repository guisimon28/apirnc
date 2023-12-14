package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.VilleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringVilleJpaRepository extends JpaRepository<VilleJpaEntity, Long> {
}