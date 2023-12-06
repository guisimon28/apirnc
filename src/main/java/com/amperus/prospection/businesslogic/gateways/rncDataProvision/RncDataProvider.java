package com.amperus.prospection.businesslogic.gateways.rncDataProvision;

import java.util.List;

import com.amperus.prospection.businesslogic.models.Copropriete;

public interface RncDataProvider {

	List<Copropriete> findAllCopropriete();
}
