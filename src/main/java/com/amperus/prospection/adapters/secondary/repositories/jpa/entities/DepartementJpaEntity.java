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
    @ManyToOne(cascade = CascadeType.ALL)
    private RegionJpaEntity region;

    public void update(Departement departement) {
        this.codeOfficiel = departement.codeOfficiel();
        this.nomOfficiel = departement.nomOfficiel();
    }

    public boolean isSame(Departement departement) {
        return codeOfficiel.equalsIgnoreCase(departement.codeOfficiel());
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
