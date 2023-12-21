package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.Adresse;
import com.amperus.prospection.businesslogic.models.Arrondissement;
import com.amperus.prospection.businesslogic.models.Departement;
import com.amperus.prospection.businesslogic.models.Ville;
import jakarta.persistence.*;

import java.util.Optional;

@Entity(name = "villes")
public class VilleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codePostal;
    private String nom;
    private String codeArrondissement;
    private String nomArrondissement;
    @ManyToOne(cascade = CascadeType.ALL)
    private DepartementJpaEntity departement;

    public DepartementJpaEntity getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementJpaEntity departement) {
        this.departement = departement;
    }

    public void update(Ville ville) {
        this.codePostal = ville.codePostal();
        this.nom = ville.nom();
        this.codeArrondissement = ville.arrondissement().codePostal();
        this.nomArrondissement = ville.arrondissement().nom();
    }

    public boolean isSame(Ville ville) {
        return codePostal.equalsIgnoreCase(ville.codePostal())
                && nom.equalsIgnoreCase(ville.nom())
                && codeArrondissement.equalsIgnoreCase(ville.arrondissement().codePostal());
    }

    Adresse.Builder convertToDomain() {
        return new Adresse.Builder()
                .codePostal(codePostal)
                .ville(convertVilleToDomain());
    }

    Arrondissement.Builder convertArrondissementToDomain() {
        return new Arrondissement.Builder()
                .codePostal(codeArrondissement)
                .nom(nomArrondissement);
    }


    Ville convertVilleToDomain() {
        Ville.Builder builder = new Ville.Builder()
                .codePostal(codePostal)
                .nom(nom)
                .arrondissement(convertArrondissementToDomain().build());
        convertDepartementToDomain().ifPresent(builder::departement);
        return builder.build();
    }

    Optional<Departement> convertDepartementToDomain() {
        if (departement != null) {
            return Optional.of(departement.convertToDomain());
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "VilleJpaEntity{" +
                "id=" + id +
                ", codePostal='" + codePostal + '\'' +
                ", nom='" + nom + '\'' +
                ", codeOfficielArrondissement='" + codeArrondissement + '\'' +
                ", nomOfficielArrondissement='" + nomArrondissement + '\'' +
                ", departement=" + departement +
                '}';
    }


}