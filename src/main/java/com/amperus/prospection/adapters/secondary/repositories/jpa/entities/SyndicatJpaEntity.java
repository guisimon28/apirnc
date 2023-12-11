package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "syndicats")
public class SyndicatJpaEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private TypeSyndicatJpaEnum type;
    private String nom;
    private String siret;
    private String codeAPE;
    private boolean cooperatif;
    private int nombreAssociationSyndicaleLibre;
    private int nombreAssociationFonciereUrbaineLibre;
    private int nombreUnionsSyndicats;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TypeSyndicatJpaEnum getType() {
        return type;
    }

    public void setType(TypeSyndicatJpaEnum type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getCodeAPE() {
        return codeAPE;
    }

    public void setCodeAPE(String codeAPE) {
        this.codeAPE = codeAPE;
    }

    public boolean isCooperatif() {
        return cooperatif;
    }

    public void setCooperatif(boolean cooperatif) {
        this.cooperatif = cooperatif;
    }

    public int getNombreAssociationSyndicaleLibre() {
        return nombreAssociationSyndicaleLibre;
    }

    public void setNombreAssociationSyndicaleLibre(int nombreAssociationSyndicaleLibre) {
        this.nombreAssociationSyndicaleLibre = nombreAssociationSyndicaleLibre;
    }

    public int getNombreAssociationFonciereUrbaineLibre() {
        return nombreAssociationFonciereUrbaineLibre;
    }

    public void setNombreAssociationFonciereUrbaineLibre(int nombreAssociationFonciereUrbaineLibre) {
        this.nombreAssociationFonciereUrbaineLibre = nombreAssociationFonciereUrbaineLibre;
    }

    public int getNombreUnionsSyndicats() {
        return nombreUnionsSyndicats;
    }

    public void setNombreUnionsSyndicats(int nombreUnionsSyndicats) {
        this.nombreUnionsSyndicats = nombreUnionsSyndicats;
    }

    @Override
    public String toString() {
        return "SyndicatJpaEntity{" +
                "id=" + id +
                ", type=" + type +
                ", nom='" + nom + '\'' +
                ", siret='" + siret + '\'' +
                ", codeAPE='" + codeAPE + '\'' +
                ", cooperatif=" + cooperatif +
                ", nombreAssociationSyndicaleLibre=" + nombreAssociationSyndicaleLibre +
                ", nombreAssociationFonciereUrbaineLibre=" + nombreAssociationFonciereUrbaineLibre +
                ", nombreUnionsSyndicats=" + nombreUnionsSyndicats +
                '}';
    }
}
