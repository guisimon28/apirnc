package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import java.io.Serializable;
import java.util.UUID;

public class MandatIdClass implements Serializable {
    private UUID copropriete;
    private UUID syndicat;

    public UUID getCopropriete() {
        return copropriete;
    }

    public void setCopropriete(UUID copropriete) {
        this.copropriete = copropriete;
    }

    public UUID getSyndicat() {
        return syndicat;
    }

    public void setSyndicat(UUID syndicat) {
        this.syndicat = syndicat;
    }
}
