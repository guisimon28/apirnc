package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;


import com.amperus.prospection.businesslogic.models.InformationCadastrale;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "informations_cadastrales")
public class InformationCadastraleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private String codeInseeCommune;
    private String prefixe;
    private String section;
    private String numeroParcelle;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void update(InformationCadastrale informationCadastrale) {
        this.numeroParcelle = informationCadastrale.numeroParcelle();
        this.codeInseeCommune = informationCadastrale.codeINSEECommune();
        this.section = informationCadastrale.section();
        this.prefixe = informationCadastrale.prefixe();
        this.reference = informationCadastrale.reference();
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
