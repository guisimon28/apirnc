package com.amperus.prospection.businesslogic.usecases;

import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.gateways.rncDataProvision.RncDataProvider;
import com.amperus.prospection.businesslogic.models.Copropriete;

import java.util.List;

public class ImportRegistreNationalCopropriete {

	private final RncDataProvider rncDataProvider;
	private final CoproprieteRepository coproprieteRepository;

	public ImportRegistreNationalCopropriete(RncDataProvider rncDataProvider, CoproprieteRepository coproprieteRepository) {
		this.rncDataProvider = rncDataProvider;
		this.coproprieteRepository = coproprieteRepository;
	}

	public void handle() {
		List<Copropriete> coproprietes = rncDataProvider.findAllCopropriete();
		coproprieteRepository.saveAll(coproprietes);
	}
}
