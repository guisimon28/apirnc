package com.amperus.prospection.businesslogic.usecases;

import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPage;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPageable;

import java.util.Optional;

public class GetCopropriete {
    private final CoproprieteRepository coproprieteRepository;

    public GetCopropriete(CoproprieteRepository coproprieteRepository) {
        this.coproprieteRepository = coproprieteRepository;
    }

    public Optional<Copropriete> byNumeroImmatriculation(String numeroImmatriculation) {
        return coproprieteRepository.findByNumeroImmatriculation(numeroImmatriculation);
    }

    public MyAppPage<Copropriete> findAllCoproprietes(MyAppPageable myAppPageable) {
        return coproprieteRepository.findAllCoproprietes(myAppPageable);
    }
}
