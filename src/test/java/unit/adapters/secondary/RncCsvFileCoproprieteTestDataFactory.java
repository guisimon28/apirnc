package unit.adapters.secondary;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.amperus.apirnc.businesslogic.models.Adresse;
import com.amperus.apirnc.businesslogic.models.Arretes;
import com.amperus.apirnc.businesslogic.models.Cadastre;
import com.amperus.apirnc.businesslogic.models.Caracteristique;
import com.amperus.apirnc.businesslogic.models.Copropriete;
import com.amperus.apirnc.businesslogic.models.Departement;
import com.amperus.apirnc.businesslogic.models.EtablissementPublicCooperationIntercommunale;
import com.amperus.apirnc.businesslogic.models.InformationCadastrale;
import com.amperus.apirnc.businesslogic.models.Lots;
import com.amperus.apirnc.businesslogic.models.QuartierPrioritaire;
import com.amperus.apirnc.businesslogic.models.Region;
import com.amperus.apirnc.businesslogic.models.RepresentantLegal;
import com.amperus.apirnc.businesslogic.models.Syndicat;
import com.amperus.apirnc.businesslogic.models.TypeSyndicat;
import com.amperus.apirnc.businesslogic.models.Ville;

class RncCsvFileCoproprieteTestDataFactory {

	public static EtablissementPublicCooperationIntercommunale.Builder anEPCI() {
		return new EtablissementPublicCooperationIntercommunale.Builder()
				.siren("246900724")
				.codeOfficiel("246900724")
				.nomOfficiel("CC des Vallons du Lyonnais (CCVL)");
	}

	public static RepresentantLegal.Builder aRepresentantLegal() {
		return new RepresentantLegal.Builder()
				.nom("John Doe")
				.siret("123456789")
				.codeAPE("1234")
				.commune("Paris");
	}

	public static Syndicat.Builder aSyndicat() {
		return new Syndicat.Builder()
				.type(TypeSyndicat.BENEVOLE)
				.representantLegal(aRepresentantLegal().build())
				.cooperatif(true)
				.principalOuSecondaire(false)
				.nombreAssociationSyndicaleLibre(5)
				.nombreAssociationFonciereUrbaineLibre(3)
				.nombreUnionsSyndicats(2)
				.mandatEnCours("2023-2025");
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

	public static QuartierPrioritaire.Builder aQuartierPrioritaire() {
		return new QuartierPrioritaire.Builder()
				.nom("NomQuartier")
				.code("CodeQuartier");
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
				.ville(aVille().build());
	}

	public static Caracteristique.Builder aCaracteristique() {
		return new Caracteristique.Builder()
				.dateReglement(LocalDate.of(2023, 11, 6))
				.residenceService(true)
				.numeroImmatriculationPrincipal(true)
				.periodeConstruction("2022-2025")
				.dansActionCoeurDeVille(true)
				.dansPetiteVilleDeDemain(false)
				.aidee(true);
	}

	public static Arretes.Builder anArretes() {
		return new Arretes.Builder()
				.nombreSantePubliqueEnCours(5)
				.nombrePerilPartiesCommunes(3)
				.nombreEquipementsCommunsEnCours(2);
	}

	public static Copropriete.Builder aCopropriete() {
		return new Copropriete.Builder()
				.numeroImmatriculation("AA0002402")
				.nomUsage("LES JARDINS DE JUSTINE")
				.epci(anEPCI().build())
				.cadastre(aCadastre().build())
				.adresse(anAdresse().build())
				.lots(aLots().build());
	}

}