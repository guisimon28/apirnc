package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;

import com.amperus.prospection.businesslogic.models.Syndicat;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity(name = "syndicats")
public class SyndicatJpaEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;
    @Enumerated(EnumType.STRING)
    private TypeSyndicatJpaEnum type;
    private String raisonSociale;
    private String siret;
    private String codeAPE;
    private boolean cooperatif;

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

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
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

    public void update(Syndicat syndicat) {
        this.raisonSociale = syndicat.raisonSociale();
        this.siret = syndicat.siret();
        this.codeAPE = syndicat.codeAPE();
        this.type = TypeSyndicatJpaEnum.convertFromModel(syndicat.type());
        this.cooperatif = syndicat.cooperatif();
    }

    @Override
    public String toString() {
        return "SyndicatJpaEntity{" +
                "id=" + id +
                ", type=" + type +
                ", raisonSociale='" + raisonSociale + '\'' +
                ", siret='" + siret + '\'' +
                ", codeAPE='" + codeAPE + '\'' +
                ", cooperatif=" + cooperatif +
                '}';
    }
}
