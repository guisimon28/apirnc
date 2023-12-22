package com.amperus.prospection.businesslogic.gateways.repositories;

import com.amperus.prospection.businesslogic.models.Copropriete;

import java.util.List;
import java.util.Optional;

public interface CoproprieteRepository {

	void saveAll(List<Copropriete> copropriete);

    Optional<Copropriete> findByNumeroImmatriculation(String numeroImmatriculation);
}
