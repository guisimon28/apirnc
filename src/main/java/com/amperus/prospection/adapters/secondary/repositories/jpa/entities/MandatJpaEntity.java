package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;


import com.amperus.prospection.businesslogic.models.Mandat;
import jakarta.persistence.*;

@Entity(name = "mandats")
@IdClass(MandatIdClass.class)
public class MandatJpaEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "copropriete_id")
    private CoproprieteJpaEntity copropriete;
    @Id
    @ManyToOne
    @JoinColumn(name = "syndicat_id")
    private SyndicatJpaEntity syndicat;
    @Enumerated(EnumType.STRING)
    private MandatStatutJpaEnum statut;

    public CoproprieteJpaEntity getCopropriete() {
        return copropriete;
    }

    public void setCopropriete(CoproprieteJpaEntity copropriete) {
        this.copropriete = copropriete;
    }

    public SyndicatJpaEntity getSyndicat() {
        return syndicat;
    }

    public void setSyndicat(SyndicatJpaEntity syndicat) {
        this.syndicat = syndicat;
    }

    public void update(Mandat mandat) {
        this.statut = MandatStatutJpaEnum.convertFromModel(mandat.statut());
    }

    public Mandat convertToDomain() {
        return new Mandat.Builder()
                .statut(MandatStatutJpaEnum.convertToDomain(statut))
                .syndicat(syndicat.convertToDomain())
                .build();
    }

    @Override
    public String toString() {
        return "MandatJpaEntity{" +
                "copropriete=" + copropriete +
                ", syndicat=" + syndicat +
                ", statut=" + statut +
                '}';
    }

}
