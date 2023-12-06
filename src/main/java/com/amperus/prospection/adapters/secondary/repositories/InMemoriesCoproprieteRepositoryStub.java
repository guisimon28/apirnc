package com.amperus.prospection.adapters.secondary.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Copropriete;

public class InMemoriesCoproprieteRepositoryStub implements CoproprieteRepository {

	private final Map<String, Copropriete> coproprieteByImmatriculation = new HashMap<>();

	@Override
	public void saveAll(List<Copropriete> coproprietes) {
		coproprietes.forEach(copropriete -> coproprieteByImmatriculation.put(copropriete.numeroImmatriculation(), copropriete));
	}

	// SECRET METHOD
	public List<Copropriete> findAll() {
		return new ArrayList<>(coproprieteByImmatriculation.values());
	}

	public void setCoproprietes(Copropriete... coproprietes) {
		Arrays.stream(coproprietes).forEach(copropriete -> coproprieteByImmatriculation.put(copropriete.numeroImmatriculation(), copropriete));
	}
}