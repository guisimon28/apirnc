package com.amperus.prospection.businesslogic.gateways.banprovision;

import java.util.Optional;

public interface BanDataProvider {

    Optional<String> findNumeroEtVoie(Geolocalisation geolocalisation);
}
