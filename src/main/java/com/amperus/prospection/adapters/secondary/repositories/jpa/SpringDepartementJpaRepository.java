package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.DepartementJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDepartementJpaRepository extends JpaRepository<DepartementJpaEntity, Long> {
}