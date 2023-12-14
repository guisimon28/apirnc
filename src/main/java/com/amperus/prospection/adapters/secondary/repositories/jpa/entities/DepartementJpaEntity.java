package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.Departement;
import jakarta.persistence.*;

@Entity(name = "departements")
public class DepartementJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeOfficiel;
    private String nomOfficiel;

    @ManyToOne(fetch = FetchType.LAZY)
    private RegionJpaEntity region;

    public DepartementJpaEntity() {
    }

    public DepartementJpaEntity(Departement departement) {
        this.codeOfficiel = departement.codeOfficiel();
        this.nomOfficiel = departement.nomOfficiel();
        this.region = new RegionJpaEntity(departement.region());
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
