package unit.usecases;

import java.time.LocalDate;
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

class CoproprieteTestDataFactory {

	public static EtablissementPublicCooperationIntercommunale.Builder anEPCI() {
		return new EtablissementPublicCooperationIntercommunale.Builder()
				.siret("123456789")
				.codeOfficiel("EPCI001")
				.nomOfficiel("EPCI A");
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
				.nombreTotal(100)
				.nombreUsageHabitationBureauxCommerces(50)
				.nombreUsageHabitation(30)
				.nombreStationnement(20);
	}

	public static Cadastre.Builder aCadastre() {
		return new Cadastre.Builder()
				.informations(List.of(new InformationCadastrale("Ref1", 12345, "A", "A1", "001")))
				.nombreParcelles(1);
	}

	public static QuartierPrioritaire.Builder aQuartierPrioritaire() {
		return new QuartierPrioritaire.Builder()
				.nom("NomQuartier")
				.code("CodeQuartier");
	}

	public static Ville.Builder aVille() {
		return new Ville.Builder()
				.codeOfficiel("12345")
				.nomOfficiel("Ville A")
				.codeOfficielArrondissement("A1")
				.nomOfficielArrondissement("Arrondissement 1")
				.departement(new Departement("D123", "Département A"))
				.region(new Region("R123", "Région A"));
	}

	public static Adresse.Builder anAdresse() {
		return new Adresse.Builder()
				.numeroEtVoie("123 Rue de la Copropriété")
				.codePostal("75000")
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
				.numeroImmatriculation("12345")
				.nomUsage("Copropriété A")
				.epci(anEPCI().build())
				.syndicat(aSyndicat().build())
				.lots(aLots().build())
				.cadastre(aCadastre().build())
				.quartierPrioritaire(aQuartierPrioritaire().build())
				.adresse(anAdresse().build())
				.caracteristique(aCaracteristique().build())
				.arretes(anArretes().build());
	}

}