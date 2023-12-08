package com.amperus.prospection.adapters.secondary.rncprovision;

import com.amperus.prospection.businesslogic.gateways.rncDataProvision.RncDataProvider;
import com.amperus.prospection.businesslogic.models.Copropriete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoriesRncDataProviderStub implements RncDataProvider {

    private final List<Copropriete> coproprietes = new ArrayList<>();

    @Override
    public List<Copropriete> findAllCopropriete() {
        return coproprietes;
    }

    // SECRET METHOD
    public void setCoproprietes(Copropriete... coproprietes) {
        Collections.addAll(this.coproprietes, coproprietes);
    }
}
