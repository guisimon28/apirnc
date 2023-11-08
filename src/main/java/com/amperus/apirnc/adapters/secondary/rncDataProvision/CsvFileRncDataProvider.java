package com.amperus.apirnc.adapters.secondary.rncDataProvision;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.amperus.apirnc.businesslogic.gateways.rncDataProvision.RncDataProvider;
import com.amperus.apirnc.businesslogic.models.Adresse;
import com.amperus.apirnc.businesslogic.models.Cadastre;
import com.amperus.apirnc.businesslogic.models.Copropriete;
import com.amperus.apirnc.businesslogic.models.Departement;
import com.amperus.apirnc.businesslogic.models.EtablissementPublicCooperationIntercommunale;
import com.amperus.apirnc.businesslogic.models.InformationCadastrale;
import com.amperus.apirnc.businesslogic.models.Lots;
import com.amperus.apirnc.businesslogic.models.Region;
import com.amperus.apirnc.businesslogic.models.Ville;

public class CsvFileRncDataProvider implements RncDataProvider {

	private URL csvFileUrl;

	public void setCsvFileUrl(URL csvFileUrl) {
		this.csvFileUrl = csvFileUrl;
	}

	@Override
	public List<Copropriete> findAllCopropriete() {
		List<Copropriete> coproprietes = new ArrayList<>();
		try {
			InputStreamReader reader = new InputStreamReader(csvFileUrl.openStream(), StandardCharsets.UTF_8);
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
			for (CSVRecord csvRecord : csvParser) {
				Copropriete copropriete = getCoproprieteFromRecord(csvRecord);
				coproprietes.add(copropriete);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return coproprietes;
	}

	private Copropriete getCoproprieteFromRecord(CSVRecord csvRecord) {
		Adresse adresse = getAdresseFromRecord(csvRecord);
		return new  Copropriete.Builder()
				.numeroImmatriculation(csvRecord.get("Numéro d'immatriculation"))
				.nomUsage(csvRecord.get("Nom d’usage de la copropriété"))
				.epci(getEpciFromRecord(csvRecord))
				.cadastre(getCadastreFromRecord(csvRecord))
				.lots(getLotsFromRecord(csvRecord))
				.adresse(adresse).build();
	}

	private EtablissementPublicCooperationIntercommunale getEpciFromRecord(CSVRecord csvRecord) {
		return new EtablissementPublicCooperationIntercommunale.Builder()
				.siren(csvRecord.get("EPCI"))
				.codeOfficiel(csvRecord.get("Code Officiel EPCI"))
				.nomOfficiel(csvRecord.get("Nom Officiel EPCI")).build();
	}

	private Cadastre getCadastreFromRecord(CSVRecord csvRecord) {
		List<InformationCadastrale> informations = getInformationsCadastralesFromRecord(csvRecord);
		int nombreParcelles = Integer.parseInt(csvRecord.get("Nombre de parcelles cadastrales"));
		return new Cadastre.Builder()
				.informations(informations)
				.nombreParcelles(nombreParcelles).build();
	}

	private List<InformationCadastrale> getInformationsCadastralesFromRecord(CSVRecord csvRecord) {
		return Stream.of(getInformationsCadastralesFromRecordAndIndex(csvRecord, 1), getInformationsCadastralesFromRecordAndIndex(csvRecord, 2), getInformationsCadastralesFromRecordAndIndex(csvRecord, 3))
				.filter(Optional::isPresent)
				.map(Optional::get).toList();
	}

	private Optional<InformationCadastrale> getInformationsCadastralesFromRecordAndIndex(CSVRecord csvRecord, int index) {
		InformationCadastrale informationCadastrale = new InformationCadastrale.Builder()
				.reference(csvRecord.get("Référence Cadastrale "+ index))
				.codeINSEECommune(csvRecord.get("Code INSEE commune " + index))
				.prefixe(csvRecord.get("Préfixe "+ index))
				.section(csvRecord.get("Section "+ index))
				.numeroParcelle(csvRecord.get("Numéro parcelle "+ index)).build();
		if(!informationCadastrale.numeroParcelle().isEmpty() && !informationCadastrale.codeINSEECommune().isEmpty() && !informationCadastrale.prefixe()
				.isEmpty() && !informationCadastrale.section().isEmpty() && !informationCadastrale.reference().isEmpty()){
			return Optional.of(informationCadastrale);
		}
		return Optional.empty();
	}

	private Lots getLotsFromRecord(CSVRecord csvRecord) {
		return new Lots.Builder()
				.nombreTotal(Integer.parseInt(csvRecord.get("Nombre total de lots")))
				.nombreStationnement(Integer.parseInt(csvRecord.get("Nombre de lots de stationnement")))
				.nombreUsageHabitation(Integer.parseInt(csvRecord.get("Nombre de lots à usage d’habitation")))
				.nombreUsageHabitationBureauxCommerces(Integer.parseInt(csvRecord.get("Nombre total de lots à usage d’habitation, de bureaux ou de commerces")))
				.build();
	}

	private Adresse getAdresseFromRecord(CSVRecord csvRecord) {
		Ville ville = getVilleFromRecord(csvRecord);
		return new Adresse.Builder()
				.numeroEtVoie(csvRecord.get("Numéro et Voie (adresse de référence)"))
				.codePostal(csvRecord.get("Code postal (adresse de référence)"))
				.ville(ville).build();
	}

	private Ville getVilleFromRecord(CSVRecord csvRecord) {
		Departement departement = new Departement.Builder()
				.codeOfficiel(csvRecord.get("Code Officiel Département"))
				.nomOfficiel(csvRecord.get("Nom Officiel Département"))
				.build();

		Region region = new Region.Builder()
				.codeOfficiel(csvRecord.get("Code Officiel Région"))
				.nomOfficiel(csvRecord.get("Nom Officiel Région"))
				.build();

		return new Ville.Builder()
				.codeOfficiel(csvRecord.get("Code Officiel Commune"))
				.nomOfficiel(csvRecord.get("Nom Officiel Commune"))
				.codeOfficielArrondissement(csvRecord.get("Code Officiel Arrondissement Commune"))
				.nomOfficielArrondissement(csvRecord.get("Nom Officiel Arrondissement Commune"))
				.departement(departement)
				.region(region)
				.build();
	}

}