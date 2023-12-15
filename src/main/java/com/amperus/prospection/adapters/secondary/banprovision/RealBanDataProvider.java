package com.amperus.prospection.adapters.secondary.banprovision;

import com.amperus.prospection.businesslogic.gateways.banprovision.BanDataProvider;
import com.amperus.prospection.businesslogic.gateways.banprovision.Geolocalisation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class RealBanDataProvider implements BanDataProvider {

    private final String banUrl;

    public RealBanDataProvider(String banUrl) {
        this.banUrl = banUrl;
    }

    @Override
    public Optional<String> findNumeroEtVoie(Geolocalisation geolocalisation) {
        return getFirstPropertyName(geolocalisation);
    }

    public Optional<String> getFirstPropertyName(Geolocalisation geolocalisation) {
        String url = String.format("%slat=%s&lon=%s", banUrl, geolocalisation.latitude(), geolocalisation.longitude());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BanResponse> response = restTemplate.getForEntity(url, BanResponse.class);
        return Optional.of(response)
                .map(ResponseEntity::getBody)
                .map(BanResponse::getFeatures)
                .filter(Predicate.not(List::isEmpty))
                .flatMap(features -> features.stream().findFirst())
                .map(Feature::getProperties)
                .map(Properties::getName);
    }
}
