package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;


import com.amperus.prospection.businesslogic.models.InformationCadastrale;
import jakarta.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "copropriete_id")
    private CoproprieteJpaEntity copropriete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public CoproprieteJpaEntity getCopropriete() {
        return copropriete;
    }

    public void setCopropriete(CoproprieteJpaEntity copropriete) {
        this.copropriete = copropriete;
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
