package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity(name = "cadastres")
public class CadastreJpaEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private int nombreParcelles;
    @OneToMany(mappedBy = "cadastre", orphanRemoval = true)
    private List<InformationCadastraleJpaEntity> informationsCadastrales;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNombreParcelles() {
        return nombreParcelles;
    }

    public void setNombreParcelles(int nombreParcelles) {
        this.nombreParcelles = nombreParcelles;
    }

    public List<InformationCadastraleJpaEntity> getInformationsCadastrales() {
        return informationsCadastrales;
    }

    public void setInformationsCadastrales(List<InformationCadastraleJpaEntity> informationsCadastrales) {
        this.informationsCadastrales = informationsCadastrales;
    }

    @Override
    public String toString() {
        return "CadastreJpaEntity{" +
                "id=" + id +
                ", nombreParcelles=" + nombreParcelles +
                ", informationsCadastrales=" + informationsCadastrales +
                '}';
    }
}
