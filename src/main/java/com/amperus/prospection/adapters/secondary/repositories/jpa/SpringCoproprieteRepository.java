package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.CoproprieteJpaEntity;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteGeoLocalisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringCoproprieteRepository extends JpaRepository<CoproprieteJpaEntity, UUID> {
    @Transactional(readOnly = true)
    Optional<CoproprieteJpaEntity> findByNumeroImmatriculation(String numeroImmatriculation);

    @Query("select c.numeroImmatriculation as numeroImmatriculation, c.adresse.latitude as latitude, c.adresse.longitude as longitude " +
            "from coproprietes c where c.adresse.numeroEtVoieGpx is null and c.adresse.latitude is not null and c.adresse.longitude is not null")
    List<CoproprieteGeoLocalisation> findAllWithMissingNumeroEtVoie();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE coproprietes c SET c.adresse.numeroEtVoieGpx = :numeroEtVoie WHERE c.numeroImmatriculation = :numeroImmatriculation")
    void updateNumeroEtVoieForNumeroImmatriculation(@Param("numeroEtVoie") String numeroEtVoie, @Param("numeroImmatriculation") String numeroImmatriculation);
}