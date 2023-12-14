package com.amperus.prospection.businesslogic.usecases;

import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.gateways.rncDataProvision.RncDataProvider;
import com.amperus.prospection.businesslogic.models.Copropriete;
import org.apache.commons.lang3.time.StopWatch;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportRegistreNationalCopropriete {

	private final RncDataProvider rncDataProvider;
	private final CoproprieteRepository coproprieteRepository;

	private static final Logger LOGGER = Logger.getLogger(ImportRegistreNationalCopropriete.class.getName());

	public ImportRegistreNationalCopropriete(RncDataProvider rncDataProvider, CoproprieteRepository coproprieteRepository) {
		this.rncDataProvider = rncDataProvider;
		this.coproprieteRepository = coproprieteRepository;
	}

	public void handle() {
		LOGGER.info("Début de récupération du registre des copropriétés");
		StopWatch watch = new StopWatch();
		watch.start();
		List<Copropriete> coproprietes = rncDataProvider.findAllCopropriete();
		coproprieteRepository.saveAll(coproprietes);
		LOGGER.log(Level.INFO, "Fin de récupération en {0} sec", watch.getTime(TimeUnit.SECONDS));
	}
}
