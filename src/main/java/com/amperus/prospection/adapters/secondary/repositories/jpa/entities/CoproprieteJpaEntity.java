package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "coproprietes")
public class CoproprieteJpaEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String numeroImmatriculation;
    private String nomUsage;
    @Embedded
    private AdresseJpaEntity adresseJpaEntity;
    @ManyToOne
    private VilleJpaEntity ville;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroImmatriculation() {
        return numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    public String getNomUsage() {
        return nomUsage;
    }

    public void setNomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
    }

    public AdresseJpaEntity getAdresseJpaEntity() {
        return adresseJpaEntity;
    }

    public void setAdresseJpaEntity(AdresseJpaEntity adresseJpaEntity) {
        this.adresseJpaEntity = adresseJpaEntity;
    }

    public VilleJpaEntity getVille() {
        return ville;
    }

    public void setVille(VilleJpaEntity ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "CoproprieteJpaEntity{" +
                "id=" + id +
                ", numeroImmatriculation='" + numeroImmatriculation + '\'' +
                ", nomUsage='" + nomUsage + '\'' +
                ", adresseJpaEntity=" + adresseJpaEntity +
                ", ville=" + ville +
                '}';
    }
}