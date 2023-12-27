package com.amperus.prospection.adapters.secondary.repositories;

import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPage;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPageable;
import com.amperus.prospection.businesslogic.models.pagination.MyAppSort;
import com.amperus.prospection.businesslogic.models.pagination.MyAppSortDirection;
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

		Comparator<Copropriete> comparator = createComparator(myAppPageable.getSort());
		List<Copropriete> filteredList = stream.sorted(comparator).toList();
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

	private Comparator<Copropriete> createComparator(MyAppSort myAppSort) {
		if (myAppSort == null || myAppSort.getPath() == null) {
			return (next, previous) -> 0;
		}

		Comparator<Copropriete> comparator = switch (myAppSort.getPath()) {
			case "nomUsage" -> Comparator.comparing(Copropriete::nomUsage, Comparator.nullsLast(Comparator.naturalOrder()));
			case "numeroImmatriculation" -> Comparator.comparing(Copropriete::numeroImmatriculation, Comparator.nullsLast(Comparator.naturalOrder()));
			case "adresse" ->
					Comparator.comparing(copropriete -> copropriete.adresse() != null ? copropriete.adresse().numeroEtVoie() : null, Comparator.nullsLast(Comparator.naturalOrder()));
			case "ville" -> Comparator.comparing(copropriete -> copropriete.adresse() != null ? (copropriete.adresse().ville() != null ?
					copropriete.adresse().ville().nom() : null) : null, Comparator.nullsLast(Comparator.naturalOrder()));
			case "syndicat" -> Comparator.comparing(copropriete -> copropriete.mandat() != null ? (copropriete.mandat().syndicat() != null ?
					copropriete.mandat().syndicat().raisonSociale() : null) : null, Comparator.nullsLast(Comparator.naturalOrder()));
			default -> (copropriete1, copropriete2) -> 0;
		};

		if (myAppSort.getDirection() == MyAppSortDirection.DSC) {
			comparator = comparator.reversed();
		}

		return comparator;
	}

	// SECRET METHOD
	public List<Copropriete> findAll() {
		return new ArrayList<>(coproprieteByImmatriculation.values());
	}

	public void setCoproprietes(Copropriete... coproprietes) {
		Arrays.stream(coproprietes).forEach(copropriete -> coproprieteByImmatriculation.put(copropriete.numeroImmatriculation(), copropriete));
	}
}