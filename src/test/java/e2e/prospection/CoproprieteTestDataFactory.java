package e2e.prospection;

import com.amperus.prospection.businesslogic.models.*;

import java.time.LocalDate;
import java.util.List;

class CoproprieteTestDataFactory {

    public static Syndicat.Builder aSyndicat() {
        return new Syndicat.Builder()
                .type(TypeSyndicat.PROFESSIONNEL)
                .raisonSociale("JOSEPH BAUR IMMOBILIER")
                .cooperatif(false)
                .siret("34473827300012")
                .codeApe("6832A")
                .commune("LYON 5EME");
    }

    public static Mandat.Builder aMandat() {
        return new Mandat.Builder()
                .statut(MandatStatut.MANDAT_EN_COURS)
                .syndicat(aSyndicat().build());
    }

    public static Lots.Builder aLots() {
        return new Lots.Builder()
                .nombreTotal(100)
                .nombreUsageHabitationBureauxCommerces(50)
                .nombreUsageHabitation(30)
                .nombreStationnement(20);
    }

    public static List<InformationCadastrale> aListOfInformationCadastrales() {
        return List.of(new InformationCadastrale.Builder()
                .reference("Ref1")
                .codeINSEECommune("12345")
                .prefixe("A")
                .section("A1")
                .numeroParcelle("001")
                .build());
    }

    public static Ville.Builder aVille() {
        Region region = new Region.Builder().codeOfficiel("R123").nomOfficiel("Région A").build();
        Departement departement = new Departement.Builder().codeOfficiel("D123").nomOfficiel("Département A").region(region).build();
        return new Ville.Builder()
                .codeOfficiel("12345")
                .nomOfficiel("Ville A")
                .codeOfficielArrondissement("A1")
                .nomOfficielArrondissement("Arrondissement 1")
                .departement(departement);
    }

    public static Adresse.Builder anAdresse() {
        return new Adresse.Builder()
                .numeroEtVoie("123 Rue de la Copropriété")
                .codePostal("75000")
                .coordonneesGeographiques(new CoordonneesGeographiques(-122.254775, 37.869085))
                .ville(aVille().build());
    }

    public static Caracteristique.Builder aCaracteristique() {
        return new Caracteristique.Builder()
                .dateReglement(LocalDate.of(2023, 11, 6))
                .residenceService(true)
                .periodeConstruction(PeriodeConstructionRange.DE_1949_A_1960)
                .dansActionCoeurDeVille(true)
                .dansPetiteVilleDeDemain(false)
                .aidee(true);
    }

    public static Copropriete.Builder aCopropriete() {
        return new Copropriete.Builder()
                .numeroImmatriculation("12345")
                .nomUsage("Copropriété A")
                .mandat(aMandat().build())
                .lots(aLots().build())
                .informationsCadastrales(aListOfInformationCadastrales())
                .adresse(anAdresse().build())
                .caracteristique(aCaracteristique().build());
    }

}