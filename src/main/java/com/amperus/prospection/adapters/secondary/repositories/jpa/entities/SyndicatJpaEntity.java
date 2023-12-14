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

    public void update(Syndicat syndicat) {
        this.raisonSociale = syndicat.raisonSociale();
        this.siret = syndicat.siret();
        this.codeAPE = syndicat.codeAPE();
        this.type = TypeSyndicatJpaEnum.convertFromModel(syndicat.type());
        this.cooperatif = syndicat.cooperatif();
    }

    public boolean isSame(Syndicat syndicat) {
        return siret.equalsIgnoreCase(syndicat.siret());
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
