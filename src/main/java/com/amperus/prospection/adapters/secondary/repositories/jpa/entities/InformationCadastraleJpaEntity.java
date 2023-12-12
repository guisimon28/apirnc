package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "informations_cadastrales")
public class InformationCadastraleJpaEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String reference;
    private String codeInseeCommune;
    private String prefixe;
    private String section;
    private String numeroParcelle;
    @ManyToOne
    @JoinColumn(name = "cadastre_id")
    private CadastreJpaEntity cadastre;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCodeInseeCommune() {
        return codeInseeCommune;
    }

    public void setCodeInseeCommune(String codeInseeCommune) {
        this.codeInseeCommune = codeInseeCommune;
    }

    public String getPrefixe() {
        return prefixe;
    }

    public void setPrefixe(String prefixe) {
        this.prefixe = prefixe;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getNumeroParcelle() {
        return numeroParcelle;
    }

    public void setNumeroParcelle(String numeroParcelle) {
        this.numeroParcelle = numeroParcelle;
    }

    public CadastreJpaEntity getCadastre() {
        return cadastre;
    }

    public void setCadastre(CadastreJpaEntity cadastre) {
        this.cadastre = cadastre;
    }

    @Override
    public String toString() {
        return "InformationCadastraleJpaEntity{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", codeInseeCommune='" + codeInseeCommune + '\'' +
                ", prefixe='" + prefixe + '\'' +
                ", section='" + section + '\'' +
                ", numeroParcelle='" + numeroParcelle + '\'' +
                '}';
    }
}
