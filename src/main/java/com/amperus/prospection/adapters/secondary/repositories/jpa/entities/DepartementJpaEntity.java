package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.Departement;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity(name = "departements")
public class DepartementJpaEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String codeOfficiel;
    private String nomOfficiel;

    @ManyToOne
    private RegionJpaEntity region;

    public DepartementJpaEntity() {
    }

    public DepartementJpaEntity(Departement departement) {
        this.codeOfficiel = departement.codeOfficiel();
        this.nomOfficiel = departement.nomOfficiel();
        this.region = new RegionJpaEntity(departement.region());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodeOfficiel() {
        return codeOfficiel;
    }

    public void setCodeOfficiel(String codeOfficiel) {
        this.codeOfficiel = codeOfficiel;
    }

    public String getNomOfficiel() {
        return nomOfficiel;
    }

    public void setNomOfficiel(String nomOfficiel) {
        this.nomOfficiel = nomOfficiel;
    }

    public RegionJpaEntity getRegion() {
        return region;
    }

    public void setRegion(RegionJpaEntity region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "DepartementJpaEntity{" +
                "id=" + id +
                ", codeOfficiel='" + codeOfficiel + '\'' +
                ", nomOfficiel='" + nomOfficiel + '\'' +
                ", region=" + region +
                '}';
    }
}
