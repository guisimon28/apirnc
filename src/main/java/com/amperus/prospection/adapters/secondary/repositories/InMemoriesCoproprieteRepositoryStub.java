package com.amperus.prospection.adapters.secondary.repositories;

import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteGeoLocalisation;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Adresse;
import com.amperus.prospection.businesslogic.models.Copropriete;
import io.micrometer.common.util.StringUtils;

import java.util.*;

public class InMemoriesCoproprieteRepositoryStub implements CoproprieteRepository {

	private final Map<String, Copropriete> coproprieteByImmatriculation = new HashMap<>();

	@Override
	public void saveAll(List<Copropriete> coproprietes) {
		coproprietes.forEach(copropriete -> coproprieteByImmatriculation.put(copropriete.numeroImmatriculation(), copropriete));
	}

	@Override
	public List<CoproprieteGeoLocalisation> findAllWithMissingNumeroEtVoie() {
		return coproprieteByImmatriculation.values().stream()
				.filter(c -> StringUtils.isBlank(c.adresse().numeroEtVoie()))
				.filter(c -> c.adresse().coordonneesGeographiques() != null)
				.filter(c -> StringUtils.isBlank(c.adresse().numeroEtVoie()))
				.map(c ->
						(CoproprieteGeoLocalisation) new InMemoriesCoproprieteGeoLocalisation.Builder()
								.numeroImmatriculation(c.numeroImmatriculation())
								.latitude(c.adresse().coordonneesGeographiques().latitude())
								.longitude(c.adresse().coordonneesGeographiques().longitude())
								.build()).toList();
	}

	@Override
	public void updateNumeroEtVoie(String numeroImmatriculation, String street) {
		Copropriete copropriete = coproprieteByImmatriculation.get(numeroImmatriculation);
		if (copropriete != null) {
			var newAdresse = new Adresse.Builder().numeroEtVoie(street)
					.codePostal(copropriete.adresse().codePostal())
					.ville(copropriete.adresse().ville())
					.coordonneesGeographiques(copropriete.adresse().coordonneesGeographiques()).build();
			Copropriete newCopropriete = new Copropriete.Builder().copropriete(copropriete).adresse(newAdresse).build();
			coproprieteByImmatriculation.put(newCopropriete.numeroImmatriculation(), newCopropriete);
		}

	}

	// SECRET METHOD
	public List<Copropriete> findAll() {
		return new ArrayList<>(coproprieteByImmatriculation.values());
	}

	public void setCoproprietes(Copropriete... coproprietes) {
		Arrays.stream(coproprietes).forEach(copropriete -> coproprieteByImmatriculation.put(copropriete.numeroImmatriculation(), copropriete));
	}
}