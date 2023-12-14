package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.Region;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "regions")
public class RegionJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeOfficiel;
    private String nomOfficiel;

    public RegionJpaEntity() {
    }

    public RegionJpaEntity(Region region) {
        this.codeOfficiel = region.codeOfficiel();
        this.nomOfficiel = region.nomOfficiel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "RegionJpaEntity{" +
                "id=" + id +
                ", codeOfficiel='" + codeOfficiel + '\'' +
                ", nomOfficiel='" + nomOfficiel + '\'' +
                '}';
    }
}