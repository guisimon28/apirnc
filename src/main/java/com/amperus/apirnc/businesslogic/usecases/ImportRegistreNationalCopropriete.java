package com.amperus.apirnc.businesslogic.usecases;

import java.util.List;

import com.amperus.apirnc.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.apirnc.businesslogic.gateways.rncDataProvision.RncDataProvider;
import com.amperus.apirnc.businesslogic.models.Copropriete;

public class ImportRegistreNationalCopropriete {

	private final RncDataProvider rncDataProvider;
	private final CoproprieteRepository coproprieteRepository;

	public ImportRegistreNationalCopropriete(RncDataProvider rncDataProvider, CoproprieteRepository coproprieteRepository) {
		this.rncDataProvider = rncDataProvider;
		this.coproprieteRepository = coproprieteRepository;
	}

	public void handle(){
		List<Copropriete> coproprietes = rncDataProvider.findAllCopropriete();
		coproprieteRepository.saveAll(coproprietes);
	}
}
