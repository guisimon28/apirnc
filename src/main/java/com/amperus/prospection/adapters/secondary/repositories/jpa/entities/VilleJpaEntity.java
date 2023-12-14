package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.Ville;
import jakarta.persistence.*;

@Entity(name = "villes")
public class VilleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeOfficiel;
    private String nomOfficiel;
    private String codeOfficielArrondissement;
    private String nomOfficielArrondissement;
    @ManyToOne(cascade = CascadeType.ALL)
    private DepartementJpaEntity departement;

    public DepartementJpaEntity getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementJpaEntity departement) {
        this.departement = departement;
    }

    public void update(Ville ville) {
        this.codeOfficiel = ville.codeOfficiel();
        this.nomOfficiel = ville.nomOfficiel();
        this.codeOfficielArrondissement = ville.codeOfficielArrondissement();
        this.nomOfficielArrondissement = ville.nomOfficielArrondissement();
    }

    public boolean isSame(Ville ville) {
        return codeOfficiel.equalsIgnoreCase(ville.codeOfficiel())
                && nomOfficiel.equalsIgnoreCase(ville.nomOfficiel())
                && codeOfficielArrondissement.equalsIgnoreCase(ville.codeOfficielArrondissement());
    }

    @Override
    public String toString() {
        return "VilleJpaEntity{" +
                "id=" + id +
                ", codeOfficiel='" + codeOfficiel + '\'' +
                ", nomOfficiel='" + nomOfficiel + '\'' +
                ", codeOfficielArrondissement='" + codeOfficielArrondissement + '\'' +
                ", nomOfficielArrondissement='" + nomOfficielArrondissement + '\'' +
                ", departement=" + departement +
                '}';
    }


}