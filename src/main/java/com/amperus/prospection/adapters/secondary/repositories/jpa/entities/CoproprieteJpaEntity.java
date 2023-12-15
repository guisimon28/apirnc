package com.amperus.prospection.adapters.secondary.repositories.jpa.entities;


import com.amperus.prospection.businesslogic.models.Adresse;
import com.amperus.prospection.businesslogic.models.Caracteristique;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.Lots;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "coproprietes")
public class CoproprieteJpaEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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
    private AdresseJpaEntity adresse;
    @ManyToOne(fetch = FetchType.LAZY)
    private VilleJpaEntity ville;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "copropriete_id")
    private List<MandatJpaEntity> mandats;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "copropriete_id")
    private List<InformationCadastraleJpaEntity> informationsCadastrales;

    public AdresseJpaEntity getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseJpaEntity adresse) {
        this.adresse = adresse;
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

    public List<InformationCadastraleJpaEntity> getInformationsCadastrales() {
        return informationsCadastrales;
    }

    public void setInformationsCadastrales(List<InformationCadastraleJpaEntity> informationsCadastrales) {
        this.informationsCadastrales = informationsCadastrales;
    }

    public void update(Copropriete copropriete) {
        this.numeroImmatriculation = copropriete.numeroImmatriculation();
        this.nomUsage = copropriete.nomUsage();

        updateCaracteristique(copropriete.caracteristique());
        updateLots(copropriete.lots());
        updateAdresse(copropriete.adresse());
    }

    private void updateCaracteristique(Caracteristique caracteristique) {
        if (caracteristique != null) {
            this.dateReglement = caracteristique.dateReglement();
            this.residenceService = caracteristique.residenceService();
            this.periodeConstruction = PeriodeConstructionRangeJpaEnum.convertFromModel(caracteristique.periodeConstruction());
            this.dansActionCoeurDeVille = caracteristique.dansActionCoeurDeVille();
            this.dansPetiteVilleDeDemain = caracteristique.dansPetiteVilleDeDemain();
            this.aidee = caracteristique.aidee();
        }
    }

    private void updateLots(Lots lots) {
        if (lots != null) {
            this.nombreLotTotal = lots.nombreTotal();
            this.nombreLotUsageHabitationBureauxCommerces = lots.nombreUsageHabitationBureauxCommerces();
            this.nombreLotUsageHabitation = lots.nombreUsageHabitation();
            this.nombreLotStationnement = lots.nombreStationnement();
        }
    }

    private void updateAdresse(Adresse adresse) {
        if (this.adresse == null) {
            this.adresse = new AdresseJpaEntity();
        }
        this.adresse.update(adresse);
    }

    public void addMandat(MandatJpaEntity mandat) {
        if (mandats == null) {
            mandats = new ArrayList<>();
        }
        mandats.add(mandat);
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
                ", adresse=" + adresse +
                ", ville=" + ville +
                ", informationsCadastrales=" + informationsCadastrales +
                '}';
    }

}