package com.amperus.prospection.businesslogic.usecases;

import java.util.List;

import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.gateways.rncDataProvision.RncDataProvider;
import com.amperus.prospection.businesslogic.models.Copropriete;

public class ImportRegistreNationalCopropriete {

	private final RncDataProvider rncDataProvider;
	private final CoproprieteRepository coproprieteRepository;

	public ImportRegistreNationalCopropriete(RncDataProvider rncDataProvider, CoproprieteRepository coproprieteRepository) {
		this.rncDataProvider = rncDataProvider;
		this.coproprieteRepository = coproprieteRepository;
	}

	public void handle(String filePath){
		List<Copropriete> coproprietes = rncDataProvider.findAllCopropriete();
		coproprieteRepository.saveAll(coproprietes);
	}
}
