package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import java.io.Serializable;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MandatIdClass that = (MandatIdClass) o;
        return Objects.equals(copropriete, that.copropriete) && Objects.equals(syndicat, that.syndicat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(copropriete, syndicat);
    }
}
