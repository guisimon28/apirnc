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
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartementJpaEntity departement;

    public VilleJpaEntity() {
    }

    public VilleJpaEntity(Ville ville) {
        this.codeOfficiel = ville.codeOfficiel();
        this.nomOfficiel = ville.nomOfficiel();
        this.codeOfficielArrondissement = ville.codeOfficielArrondissement();
        this.nomOfficielArrondissement = ville.nomOfficielArrondissement();
        this.departement = new DepartementJpaEntity(ville.departement());
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

    public String getCodeOfficielArrondissement() {
        return codeOfficielArrondissement;
    }

    public void setCodeOfficielArrondissement(String codeOfficielArrondissement) {
        this.codeOfficielArrondissement = codeOfficielArrondissement;
    }

    public String getNomOfficielArrondissement() {
        return nomOfficielArrondissement;
    }

    public void setNomOfficielArrondissement(String nomOfficielArrondissement) {
        this.nomOfficielArrondissement = nomOfficielArrondissement;
    }

    public DepartementJpaEntity getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementJpaEntity departement) {
        this.departement = departement;
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