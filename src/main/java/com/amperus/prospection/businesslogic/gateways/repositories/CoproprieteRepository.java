package com.amperus.prospection.businesslogic.gateways.repositories;

import java.util.List;

import com.amperus.prospection.businesslogic.models.Copropriete;

public interface CoproprieteRepository {

	void saveAll(List<Copropriete> copropriete);
}
