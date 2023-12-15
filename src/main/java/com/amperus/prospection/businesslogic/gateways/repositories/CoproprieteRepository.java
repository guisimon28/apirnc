package com.amperus.prospection.businesslogic.gateways.repositories;

import com.amperus.prospection.businesslogic.models.Copropriete;

import java.util.List;

public interface CoproprieteRepository {

	void saveAll(List<Copropriete> copropriete);

    List<CoproprieteGeoLocalisation> findAllWithMissingNumeroEtVoie();

    void updateNumeroEtVoie(String numeroImmatriculation, String street);
}
