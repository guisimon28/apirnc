package com.amperus.prospection.adapters.secondary.repositories;

import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPage;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPageable;

import java.util.*;

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
		int totalItems = coproprieteByImmatriculation.size();
		int fromIndex = (myAppPageable.getPage() - 1) * myAppPageable.getPageSize();
		int toIndex = Math.min(fromIndex + myAppPageable.getPageSize(), totalItems);

		return MyAppPage.builder(new ArrayList<>(coproprieteByImmatriculation.values()).subList(fromIndex, toIndex))
				.currentPage(myAppPageable.getPage())
				.pageSize(myAppPageable.getPageSize())
				.totalElementsCount(coproprieteByImmatriculation.size())
				.build();
	}

	// SECRET METHOD
	public List<Copropriete> findAll() {
		return new ArrayList<>(coproprieteByImmatriculation.values());
	}

	public void setCoproprietes(Copropriete... coproprietes) {
		Arrays.stream(coproprietes).forEach(copropriete -> coproprieteByImmatriculation.put(copropriete.numeroImmatriculation(), copropriete));
	}
}