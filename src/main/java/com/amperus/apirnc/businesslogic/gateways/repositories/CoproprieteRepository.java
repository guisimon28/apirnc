package com.amperus.apirnc.businesslogic.gateways.repositories;

import java.util.ArrayList;
import java.util.List;

import com.amperus.apirnc.businesslogic.models.Copropriete;

public interface CoproprieteRepository {

	void saveAll(List<Copropriete> copropriete);
}
