package unit.prospection.adapters.secondary;

import com.amperus.prospection.businesslogic.models.*;

import java.time.LocalDate;
import java.util.Collections;

class RncCsvFileCoproprieteTestDataFactory {

    public static RepresentantLegal.Builder aRepresentantLegal() {
        return new RepresentantLegal.Builder()
                .nom("JOSEPH BAUR IMMOBILIER 34473827300012")
                .siret("34473827300012")
                .codeAPE("6832A")
                .commune("LYON 5EME");
    }

    public static Syndicat.Builder aSyndicat() {
        return new Syndicat.Builder()
                .type(TypeSyndicat.PROFESSIONNEL)
                .representantLegal(aRepresentantLegal().build())
                .cooperatif(false)
                .nombreAssociationSyndicaleLibre(5)
                .nombreAssociationFonciereUrbaineLibre(3)
                .nombreUnionsSyndicats(2);
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

    public static Cadastre.Builder aCadastre() {
        return new Cadastre.Builder()
                .informations(Collections.emptyList())
                .nombreParcelles(0);
    }

    public static Ville.Builder aVille() {
        return new Ville.Builder()
                .codeOfficiel("69094")
                .nomOfficiel("Grézieu-la-Varenne")
                .codeOfficielArrondissement("69094")
                .nomOfficielArrondissement("Grézieu-la-Varenne")
                .departement(new Departement.Builder().codeOfficiel("69").nomOfficiel("Rhône").build())
                .region(new Region.Builder().codeOfficiel("84").nomOfficiel("Auvergne-Rhône-Alpes").build());
    }

    public static Adresse.Builder anAdresse() {
        return new Adresse.Builder()
                .numeroEtVoie("1 r des forges")
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
                .nomUsage("LES JARDINS DE JUSTINE")
                .cadastre(aCadastre().build())
                .adresse(anAdresse().build())
                .caracteristique(aCaracteristique().build())
                .lots(aLots().build())
                .mandat(aMandat().build());
    }

}