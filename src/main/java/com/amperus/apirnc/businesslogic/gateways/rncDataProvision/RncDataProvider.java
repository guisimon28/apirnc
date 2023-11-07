package com.amperus.apirnc.businesslogic.gateways.rncDataProvision;

import java.util.List;

import com.amperus.apirnc.businesslogic.models.Copropriete;

public interface RncDataProvider {

	List<Copropriete> findAllCopropriete();
}
