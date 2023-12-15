package com.amperus.prospection.businesslogic.usecases;

import com.amperus.prospection.businesslogic.gateways.banprovision.BanDataProvider;
import com.amperus.prospection.businesslogic.gateways.banprovision.Geolocalisation;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteGeoLocalisation;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;

import java.util.List;
import java.util.logging.Logger;

public class CompleterNumeroEtVoie {

    private final BanDataProvider banDataProvider;
    private final CoproprieteRepository coproprieteRepository;

    private static final Logger LOGGER = Logger.getLogger(CompleterNumeroEtVoie.class.getName());

    public CompleterNumeroEtVoie(BanDataProvider banDataProvider, CoproprieteRepository coproprieteRepository) {
        this.banDataProvider = banDataProvider;
        this.coproprieteRepository = coproprieteRepository;
    }

    public void handle() {
        List<CoproprieteGeoLocalisation> allWithMissingNumeroEtVoie = coproprieteRepository.findAllWithMissingNumeroEtVoie();
        for (int index = 0; index < allWithMissingNumeroEtVoie.size(); index++) {
            var c = allWithMissingNumeroEtVoie.get(index);
            var geoLocalisation = new Geolocalisation.Builder()
                    .latitude(c.getLatitude())
                    .longitude(c.getLongitude())
                    .build();
            banDataProvider.findNumeroEtVoie(geoLocalisation)
                    .ifPresent(street -> coproprieteRepository.updateNumeroEtVoie(c.getNumeroImmatriculation(), street));
            LOGGER.info(index + "/" + allWithMissingNumeroEtVoie.size());
        }
    }
}
