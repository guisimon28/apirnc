package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.Departement;
import jakarta.persistence.*;

@Entity(name = "departements")
public class DepartementJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nom;
    @ManyToOne(cascade = CascadeType.ALL)
    private RegionJpaEntity region;

    public void update(Departement departement) {
        this.code = departement.code();
        this.nom = departement.nom();
    }

    public boolean isSame(Departement departement) {
        return code.equalsIgnoreCase(departement.code());
    }

    public RegionJpaEntity getRegion() {
        return region;
    }

    public void setRegion(RegionJpaEntity region) {
        this.region = region;
    }

    public Departement convertToDomain() {
        Departement.Builder builder = new Departement.Builder()
                .code(code)
                .nom(nom);
        if (region != null) {
            builder.region(region.convertToDomain());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "DepartementJpaEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", region=" + region +
                '}';
    }
}
