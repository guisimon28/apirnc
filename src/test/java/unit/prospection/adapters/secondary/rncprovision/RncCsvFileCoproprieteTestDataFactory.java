package unit.prospection.adapters.secondary.rncprovision;

import com.amperus.prospection.businesslogic.models.*;

import java.time.LocalDate;
import java.util.List;

class RncCsvFileCoproprieteTestDataFactory {

    public static Syndicat.Builder aSyndicat() {
        return new Syndicat.Builder()
                .type(TypeSyndicat.PROFESSIONNEL)
                .raisonSociale("Joseph Baur Immobilier")
                .cooperatif(false)
                .siret("34473827300012")
                .codeApe("6832A")
                .commune("Lyon 5eme");
    }

    public static Mandat.Builder aMandat() {
        return new Mandat.Builder()
                .statut(MandatStatut.MANDAT_EN_COURS)
                .syndicat(aSyndicat().build());
    }

    public static Lots.Builder aLots() {
        return new Lots.Builder()
                .nombreTotal(202)
                .nombreUsageHabitationBureauxCommerces(67)
                .nombreUsageHabitation(67)
                .nombreStationnement(72);
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
        Region region = new Region.Builder().code("84").nom("Auvergne-Rhône-Alpes").build();
        Departement departement = new Departement.Builder().code("69").nom("Rhône").region(region).build();
        return new Ville.Builder()
                .codePostal("69290")
                .nom("Grézieu-la-Varenne")
                .arrondissement(new Arrondissement.Builder().nom("Grézieu-la-Varenne").codePostal("69094").build())
                .departement(departement);
    }

    public static Adresse.Builder anAdresse() {
        return new Adresse.Builder()
                .numeroEtVoie("1 Rue des Forges")
                .codePostal("69290")
                .ville(aVille().build())
                .coordonneesGeographiques(aCoordonneesGeographiques().build());
    }

    public static CoordonneesGeographiques.Builder aCoordonneesGeographiques() {
        return new CoordonneesGeographiques.Builder()
                .longitude(4.688192d)
                .latitude(45.746802);
    }

    public static Caracteristique.Builder aCaracteristique() {
        return new Caracteristique.Builder()
                .dateReglement(LocalDate.of(2005, 3, 8))
                .residenceService(false)
                .periodeConstruction(PeriodeConstructionRange.DE_2001_A_2010)
                .dansActionCoeurDeVille(false)
                .dansPetiteVilleDeDemain(false)
                .aidee(false);
    }

    public static Copropriete.Builder aCopropriete() {
        return new Copropriete.Builder()
                .numeroImmatriculation("AA0002402")
                .nomUsage("Les Jardins De Justine")
                .informationsCadastrales(aListOfInformationCadastrales())
                .adresse(anAdresse().build())
                .caracteristique(aCaracteristique().build())
                .lots(aLots().build())
                .mandat(aMandat().build());
    }

}