package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.CoproprieteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringCoproprieteRepository extends JpaRepository<CoproprieteJpaEntity, UUID> {
}
