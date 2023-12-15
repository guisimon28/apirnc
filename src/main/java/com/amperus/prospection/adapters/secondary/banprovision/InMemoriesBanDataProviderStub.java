package com.amperus.prospection.adapters.secondary.banprovision;

import com.amperus.prospection.businesslogic.gateways.banprovision.BanDataProvider;
import com.amperus.prospection.businesslogic.gateways.banprovision.Geolocalisation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoriesBanDataProviderStub implements BanDataProvider {

    private final Map<Geolocalisation, String> streetByGeolocalisation = new HashMap<>();

    @Override
    public Optional<String> findNumeroEtVoie(Geolocalisation geolocalisation) {
        return Optional.ofNullable(streetByGeolocalisation.get(geolocalisation));
    }

    // SECRET METHOD
    public void addStreet(Geolocalisation geolocalisation, String street) {
        streetByGeolocalisation.put(geolocalisation, street);
    }
}
