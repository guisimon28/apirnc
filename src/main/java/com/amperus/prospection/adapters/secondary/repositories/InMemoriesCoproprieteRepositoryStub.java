package com.amperus.prospection.adapters.secondary.repositories;

import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPage;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPageable;
import io.micrometer.common.util.StringUtils;

import java.util.*;
import java.util.stream.Stream;

public class InMemoriesCoproprieteRepositoryStub implements CoproprieteRepository {

	private final Map<String, Copropriete> coproprieteByImmatriculation = new HashMap<>();

	@Override
	public void saveAll(List<Copropriete> coproprietes) {
		coproprietes.forEach(copropriete -> coproprieteByImmatriculation.put(copropriete.numeroImmatriculation(), copropriete));
	}

	@Override
	public Optional<Copropriete> findByNumeroImmatriculation(String numeroImmatriculation) {
		return Optional.ofNullable(coproprieteByImmatriculation.get(numeroImmatriculation));
	}

	@Override
	public MyAppPage<Copropriete> findAllCoproprietes(MyAppPageable myAppPageable) {
		Stream<Copropriete> stream = coproprieteByImmatriculation.values().stream();
		stream = applyFilterIfNeeded(stream, myAppPageable.getSearchTerm());

		List<Copropriete> filteredList = stream.toList();
		int totalItems = filteredList.size();
		int fromIndex = (myAppPageable.getPage() - 1) * myAppPageable.getPageSize();
		int toIndex = Math.min(fromIndex + myAppPageable.getPageSize(), totalItems);

		return MyAppPage.builder(filteredList.subList(fromIndex, toIndex))
				.currentPage(myAppPageable.getPage())
				.pageSize(myAppPageable.getPageSize())
				.totalElementsCount(filteredList.size())
				.build();
	}

	private Stream<Copropriete> applyFilterIfNeeded(Stream<Copropriete> stream, String searchTerm) {
		if (StringUtils.isBlank(searchTerm)) {
			return stream;
		}

		return stream.filter(c -> safeContains(c.numeroImmatriculation(), searchTerm)
				|| safeContains(c.nomUsage(), searchTerm)
				|| safeContains(c.adresse() != null ? c.adresse().numeroEtVoie() : null, searchTerm)
				|| safeContains(c.adresse() != null && c.adresse().ville() != null ? c.adresse().ville().nom() : null, searchTerm)
				|| safeContains(c.mandat() != null && c.mandat().syndicat() != null ? c.mandat().syndicat().raisonSociale() : null, searchTerm));
	}
	
	private static boolean safeContains(String source, String searchTerm) {
		if (source == null || searchTerm == null) {
			return false;
		}
		return source.contains(searchTerm);
	}

	// SECRET METHOD
	public List<Copropriete> findAll() {
		return new ArrayList<>(coproprieteByImmatriculation.values());
	}

	public void setCoproprietes(Copropriete... coproprietes) {
		Arrays.stream(coproprietes).forEach(copropriete -> coproprieteByImmatriculation.put(copropriete.numeroImmatriculation(), copropriete));
	}
}