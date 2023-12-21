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
    private String code;
    private String nom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void update(Region region) {
        this.code = region.code();
        this.nom = region.nom();
    }

    public boolean isSame(Region region) {
        return code.equalsIgnoreCase(region.code());
    }

    public Region convertToDomain() {
        return new Region.Builder()
                .code(code)
                .nom(nom)
                .build();
    }

    @Override
    public String toString() {
        return "RegionJpaEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }


}