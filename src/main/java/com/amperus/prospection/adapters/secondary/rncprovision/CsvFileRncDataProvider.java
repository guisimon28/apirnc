package com.amperus.prospection.adapters.secondary.rncprovision;

import com.amperus.prospection.businesslogic.gateways.rncDataProvision.RncDataProvider;
import com.amperus.prospection.businesslogic.models.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class CsvFileRncDataProvider implements RncDataProvider {

    private URL csvFileUrl;

    public void setCsvFileUrl(URL csvFileUrl) {
        this.csvFileUrl = csvFileUrl;
    }

    private static final Map<String, PeriodeConstructionRange> mappingPeriodeConstructionRange;

    static {
        mappingPeriodeConstructionRange = new HashMap<>();
        Arrays.stream(PeriodeConstructionRange.values())
                .forEach(periode -> mappingPeriodeConstructionRange.put(periode.name(), periode));
        mappingPeriodeConstructionRange.put("NON_CONNUE", PeriodeConstructionRange.INCONNUE);
        mappingPeriodeConstructionRange.put("non renseigné", PeriodeConstructionRange.INCONNUE);
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
        return new Copropriete.Builder()
                .numeroImmatriculation(csvRecord.get("Numéro d'immatriculation"))
                .nomUsage(csvRecord.get("Nom d’usage de la copropriété"))
                .cadastre(getCadastreFromRecord(csvRecord))
                .lots(getLotsFromRecord(csvRecord))
                .adresse(getAdresseFromRecord(csvRecord))
                .mandat(getMandatFromRecord(csvRecord))
                .caracteristique(getCaracteristiqueFromRecord(csvRecord))
                .build();
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
                .reference(csvRecord.get("Référence Cadastrale " + index))
                .codeINSEECommune(csvRecord.get("Code INSEE commune " + index))
                .prefixe(csvRecord.get("Préfixe " + index))
                .section(csvRecord.get("Section " + index))
                .numeroParcelle(csvRecord.get("Numéro parcelle " + index)).build();
        if (!informationCadastrale.numeroParcelle().isEmpty() && !informationCadastrale.codeINSEECommune().isEmpty() && !informationCadastrale.prefixe()
                .isEmpty() && !informationCadastrale.section().isEmpty() && !informationCadastrale.reference().isEmpty()) {
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

    private Mandat getMandatFromRecord(CSVRecord csvRecord) {
        return new Mandat.Builder()
                .statut(MandatStatut.findFromLabel(csvRecord.get("Mandat en cours dans la copropriété")).orElse(null))
                .syndicat(getSyndicatFromRecord(csvRecord))
                .build();
    }

    private Syndicat getSyndicatFromRecord(CSVRecord csvRecord) {
        return new Syndicat.Builder()
                .type(TypeSyndicat.findFromLabel(csvRecord.get("Type de syndic : bénévole / professionnel / non connu")).orElse(null))
                .cooperatif(csvRecord.get("Syndicat coopératif").equalsIgnoreCase("oui"))
                .nombreAssociationSyndicaleLibre(Integer.parseInt(csvRecord.get("Nombre d’ASL auxquelles est rattaché le syndicat de copropriétaires")))
                .nombreAssociationFonciereUrbaineLibre(Integer.parseInt(csvRecord.get("Nombre d’AFUL auxquelles est rattaché le syndicat de copropriétaires")))
                .nombreUnionsSyndicats(Integer.parseInt(csvRecord.get("Nombre d’Unions de syndicats auxquelles est rattaché le syndicat de copropriétaires")))
                .representantLegal(getRepresentantLegal(csvRecord))
                .build();
    }

    private RepresentantLegal getRepresentantLegal(CSVRecord csvRecord) {
        return new RepresentantLegal.Builder()
                .codeAPE(csvRecord.get("Code APE"))
                .nom(csvRecord.get("Identification du représentant légal  (raison sociale et le numéro SIRET du syndic professionnel ou Civilité/prénom/ nom du syndic bénévole ou coopératif)"))
                .siret(csvRecord.get("Siret représentant légal (si existe)"))
                .commune(csvRecord.get("Commune du représentant légal"))
                .build();
    }

    private Caracteristique getCaracteristiqueFromRecord(CSVRecord csvRecord) {
        return new Caracteristique.Builder()
                .dateReglement(LocalDate.parse(csvRecord.get("Date du règlement de copropriété"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .residenceService(csvRecord.get("Résidence service").equalsIgnoreCase("oui"))
                .aidee(csvRecord.get("Copro aidée").equalsIgnoreCase("oui"))
                .dansActionCoeurDeVille(csvRecord.get("Copro dans ACV").equalsIgnoreCase("oui"))
                .dansPetiteVilleDeDemain(csvRecord.get("Copro dans PVD").equalsIgnoreCase("oui"))
                .periodeConstruction(mappingPeriodeConstructionRange.get(csvRecord.get("Période de construction")))
                .build();
    }

    private Adresse getAdresseFromRecord(CSVRecord csvRecord) {
        return new Adresse.Builder()
                .numeroEtVoie(csvRecord.get("Numéro et Voie (adresse de référence)"))
                .codePostal(csvRecord.get("Code postal (adresse de référence)"))
                .ville(getVilleFromRecord(csvRecord))
                .coordonneesGeographiques(getCoordonneesGeographiques(csvRecord))
                .build();
    }

    private CoordonneesGeographiques getCoordonneesGeographiques(CSVRecord csvRecord) {
        return new CoordonneesGeographiques.Builder()
                .latitude(Double.parseDouble(csvRecord.get("lat")))
                .longitude(Double.parseDouble(csvRecord.get("long")))
                .build();
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