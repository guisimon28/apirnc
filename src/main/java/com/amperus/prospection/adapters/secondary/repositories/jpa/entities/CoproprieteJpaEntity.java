package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "coproprietes")
public class CoproprieteJpaEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String numeroImmatriculation;
    private String nomUsage;
    private LocalDate dateReglement;
    private boolean residenceService;
    @Enumerated(EnumType.STRING)
    private PeriodeConstructionRangeJpaEnum periodeConstruction;
    private boolean dansActionCoeurDeVille;
    private boolean dansPetiteVilleDeDemain;
    private boolean aidee;
    private int nombreLotTotal;
    private int nombreLotUsageHabitationBureauxCommerces;
    private int nombreLotUsageHabitation;
    private int nombreLotStationnement;
    @Embedded
    private AdresseJpaEntity adresseJpaEntity;
    @ManyToOne
    private VilleJpaEntity ville;
    @OneToMany(mappedBy = "copropriete")
    private List<MandatJpaEntity> mandats;
    @OneToOne
    @JoinColumn(name = "cadastre_id", referencedColumnName = "id")
    private CadastreJpaEntity cadastre;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroImmatriculation() {
        return numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    public String getNomUsage() {
        return nomUsage;
    }

    public void setNomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
    }

    public LocalDate getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(LocalDate dateReglement) {
        this.dateReglement = dateReglement;
    }

    public boolean isResidenceService() {
        return residenceService;
    }

    public void setResidenceService(boolean residenceService) {
        this.residenceService = residenceService;
    }

    public PeriodeConstructionRangeJpaEnum getPeriodeConstruction() {
        return periodeConstruction;
    }

    public void setPeriodeConstruction(PeriodeConstructionRangeJpaEnum periodeConstruction) {
        this.periodeConstruction = periodeConstruction;
    }

    public boolean isDansActionCoeurDeVille() {
        return dansActionCoeurDeVille;
    }

    public void setDansActionCoeurDeVille(boolean dansActionCoeurDeVille) {
        this.dansActionCoeurDeVille = dansActionCoeurDeVille;
    }

    public boolean isDansPetiteVilleDeDemain() {
        return dansPetiteVilleDeDemain;
    }

    public void setDansPetiteVilleDeDemain(boolean dansPetiteVilleDeDemain) {
        this.dansPetiteVilleDeDemain = dansPetiteVilleDeDemain;
    }

    public boolean isAidee() {
        return aidee;
    }

    public void setAidee(boolean aidee) {
        this.aidee = aidee;
    }

    public int getNombreLotTotal() {
        return nombreLotTotal;
    }

    public void setNombreLotTotal(int nombreLotTotal) {
        this.nombreLotTotal = nombreLotTotal;
    }

    public int getNombreLotUsageHabitationBureauxCommerces() {
        return nombreLotUsageHabitationBureauxCommerces;
    }

    public void setNombreLotUsageHabitationBureauxCommerces(int nombreLotUsageHabitationBureauxCommerces) {
        this.nombreLotUsageHabitationBureauxCommerces = nombreLotUsageHabitationBureauxCommerces;
    }

    public int getNombreLotUsageHabitation() {
        return nombreLotUsageHabitation;
    }

    public void setNombreLotUsageHabitation(int nombreLotUsageHabitation) {
        this.nombreLotUsageHabitation = nombreLotUsageHabitation;
    }

    public int getNombreLotStationnement() {
        return nombreLotStationnement;
    }

    public void setNombreLotStationnement(int nombreLotStationnement) {
        this.nombreLotStationnement = nombreLotStationnement;
    }

    public AdresseJpaEntity getAdresseJpaEntity() {
        return adresseJpaEntity;
    }

    public void setAdresseJpaEntity(AdresseJpaEntity adresseJpaEntity) {
        this.adresseJpaEntity = adresseJpaEntity;
    }

    public VilleJpaEntity getVille() {
        return ville;
    }

    public void setVille(VilleJpaEntity ville) {
        this.ville = ville;
    }

    public List<MandatJpaEntity> getMandats() {
        return mandats;
    }

    public void setMandats(List<MandatJpaEntity> mandats) {
        this.mandats = mandats;
    }

    public CadastreJpaEntity getCadastre() {
        return cadastre;
    }

    public void setCadastre(CadastreJpaEntity cadastre) {
        this.cadastre = cadastre;
    }

    @Override
    public String toString() {
        return "CoproprieteJpaEntity{" +
                "id=" + id +
                ", numeroImmatriculation='" + numeroImmatriculation + '\'' +
                ", nomUsage='" + nomUsage + '\'' +
                ", dateReglement=" + dateReglement +
                ", residenceService=" + residenceService +
                ", periodeConstruction=" + periodeConstruction +
                ", dansActionCoeurDeVille=" + dansActionCoeurDeVille +
                ", dansPetiteVilleDeDemain=" + dansPetiteVilleDeDemain +
                ", aidee=" + aidee +
                ", nombreLotTotal=" + nombreLotTotal +
                ", nombreLotUsageHabitationBureauxCommerces=" + nombreLotUsageHabitationBureauxCommerces +
                ", nombreLotUsageHabitation=" + nombreLotUsageHabitation +
                ", nombreLotStationnement=" + nombreLotStationnement +
                ", adresseJpaEntity=" + adresseJpaEntity +
                ", ville=" + ville +
                ", cadastre=" + cadastre +
                '}';
    }
}