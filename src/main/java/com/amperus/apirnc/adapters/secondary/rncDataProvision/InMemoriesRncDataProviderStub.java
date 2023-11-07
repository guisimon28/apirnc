package com.amperus.apirnc.adapters.secondary.rncDataProvision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.amperus.apirnc.businesslogic.gateways.rncDataProvision.RncDataProvider;
import com.amperus.apirnc.businesslogic.models.Copropriete;

public class InMemoriesRncDataProviderStub implements RncDataProvider {

	private final List<Copropriete> coproprietes = new ArrayList<>();

	@Override
	public List<Copropriete> findAllCopropriete() {
		return coproprietes;
	}

	// SECRET METHOD
	public void setCoproprietes(Copropriete... coproprietes) {
		Collections.addAll(this.coproprietes, coproprietes);
	}
}
