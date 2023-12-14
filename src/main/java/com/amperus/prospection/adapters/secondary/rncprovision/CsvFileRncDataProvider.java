package com.amperus.prospection.adapters.secondary.rncprovision;

import com.amperus.prospection.businesslogic.gateways.rncDataProvision.RncDataProvider;
import com.amperus.prospection.businesslogic.models.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static com.amperus.prospection.adapters.secondary.rncprovision.CsvHeader.*;
import static com.amperus.prospection.adapters.secondary.rncprovision.CsvParserUtils.*;

public class CsvFileRncDataProvider implements RncDataProvider {

    private URL csvFileUrl;

    private static final Map<String, PeriodeConstructionRange> mappingPeriodeConstructionRange;

    private static final Logger LOGGER = Logger.getLogger(CsvFileRncDataProvider.class.getName());

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
        try (InputStreamReader reader = new InputStreamReader(csvFileUrl.openStream(), StandardCharsets.UTF_8)) {
            for (CSVRecord csvRecord : CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader).getRecords()) {
                extractRecord(csvRecord, coproprietes);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Une erreur s'est produite lors de la lecture du fichier Rnc csv", e);
        }

        return coproprietes;
    }

    private void extractRecord(CSVRecord csvRecord, List<Copropriete> coproprietes) {
        try {
            Copropriete copropriete = getCoproprieteFromRecord(csvRecord);
            coproprietes.add(copropriete);
        } catch (NumberFormatException | DateTimeParseException e) {
            LOGGER.log(Level.SEVERE, "Erreur de format lors de l'intégration de l'enregistrement: " + csvRecord, e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur inattendue lors de l'intégration de l'enregistrement: " + csvRecord, e);
        }
    }

    private Copropriete getCoproprieteFromRecord(CSVRecord csvRecord) {
        return new Copropriete.Builder()
                .numeroImmatriculation(csvRecord.get(NUMERO_IMMATRICULATION.getLabel()))
                .nomUsage(csvRecord.get(NOM_USAGE.getLabel()))
                .informationsCadastrales(getInformationsCadastralesFromRecord(csvRecord))
                .lots(getLotsFromRecord(csvRecord))
                .adresse(getAdresseFromRecord(csvRecord))
                .mandat(getMandatFromRecord(csvRecord))
                .caracteristique(getCaracteristiqueFromRecord(csvRecord))
                .build();
    }

    private List<InformationCadastrale> getInformationsCadastralesFromRecord(CSVRecord csvRecord) {
        return IntStream.rangeClosed(1, 3)
                .mapToObj(index -> getInformationsCadastralesFromRecordAndIndex(csvRecord, index))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private Optional<InformationCadastrale> getInformationsCadastralesFromRecordAndIndex(CSVRecord csvRecord, int index) {
        InformationCadastrale informationCadastrale = new InformationCadastrale.Builder()
                .reference(csvRecord.get(REFERENCE_CADASTRALE.getLabel() + index))
                .codeINSEECommune(csvRecord.get(CODE_INSEE_COMMUNE.getLabel() + index))
                .prefixe(csvRecord.get(PREFIXE.getLabel() + index))
                .section(csvRecord.get(SECTION.getLabel() + index))
                .numeroParcelle(csvRecord.get(NUMERO_PARCELLE.getLabel() + index)).build();
        if (!informationCadastrale.reference().isEmpty()) {
            return Optional.of(informationCadastrale);
        }
        return Optional.empty();
    }

    private Lots getLotsFromRecord(CSVRecord csvRecord) {
        return new Lots.Builder()
                .nombreTotal(parseInt(csvRecord.get(NOMBRE_TOTAL_LOTS.getLabel())))
                .nombreStationnement(parseInt(csvRecord.get(NOMBRE_LOTS_STATIONNEMENT.getLabel())))
                .nombreUsageHabitation(parseInt(csvRecord.get(NOMBRE_LOTS_HABITATION.getLabel())))
                .nombreUsageHabitationBureauxCommerces(parseInt(csvRecord.get(NOMBRE_LOTS_HABITATION_BUREAUX_COMMERCES.getLabel())))
                .build();
    }

    private Mandat getMandatFromRecord(CSVRecord csvRecord) {
        return new Mandat.Builder()
                .statut(MandatStatut.findFromLabel(csvRecord.get(MANDAT_EN_COURS.getLabel())).orElse(null))
                .syndicat(getSyndicatFromRecord(csvRecord))
                .build();
    }

    private Syndicat getSyndicatFromRecord(CSVRecord csvRecord) {
        if (csvRecord.get(SIRET_REPRESENTANT.getLabel()).equalsIgnoreCase("non connu")) {
            return null;
        }
        return new Syndicat.Builder()
                .raisonSociale(csvRecord.get(IDENTIFICATION_REPRESENTANT.getLabel()))
                .codeApe(csvRecord.get(CODE_APE.getLabel()))
                .siret(csvRecord.get(SIRET_REPRESENTANT.getLabel()))
                .commune(csvRecord.get(COMMUNE_REPRESENTANT.getLabel()))
                .type(TypeSyndicat.findFromLabel(csvRecord.get(TYPE_SYNDIC.getLabel())).orElse(null))
                .cooperatif(parseBoolean(csvRecord.get(SYNDICAT_COOPERATIF.getLabel())))
                .build();
    }

    private Caracteristique getCaracteristiqueFromRecord(CSVRecord csvRecord) {
        return new Caracteristique.Builder()
                .dateReglement(parseLocalDate(csvRecord.get(DATE_REGLEMENT.getLabel()), "yyyy-MM-dd").orElse(null))
                .residenceService(parseBoolean(csvRecord.get(RESIDENCE_SERVICE.getLabel())))
                .aidee(parseBoolean(csvRecord.get(COPRO_AIDEE.getLabel())))
                .dansActionCoeurDeVille(parseBoolean(csvRecord.get(COPRO_ACV.getLabel())))
                .dansPetiteVilleDeDemain(parseBoolean(csvRecord.get(COPRO_PVD.getLabel())))
                .periodeConstruction(mappingPeriodeConstructionRange.get(csvRecord.get(PERIODE_CONSTRUCTION.getLabel())))
                .build();
    }

    private Adresse getAdresseFromRecord(CSVRecord csvRecord) {
        return new Adresse.Builder()
                .numeroEtVoie(csvRecord.get(NUMERO_ET_VOIE.getLabel()))
                .codePostal(csvRecord.get(CODE_POSTAL.getLabel()))
                .ville(getVilleFromRecord(csvRecord))
                .coordonneesGeographiques(getCoordonneesGeographiques(csvRecord))
                .build();
    }

    private CoordonneesGeographiques getCoordonneesGeographiques(CSVRecord csvRecord) {
        return new CoordonneesGeographiques.Builder()
                .latitude(parseDouble(csvRecord.get(LATITUDE.getLabel())))
                .longitude(parseDouble(csvRecord.get(LONGITUDE.getLabel())))
                .build();
    }

    private Ville getVilleFromRecord(CSVRecord csvRecord) {
        Region region = getRegionFromRecord(csvRecord);
        Departement departement = getDepartementFromRecord(csvRecord, region);
        return new Ville.Builder()
                .codeOfficiel(csvRecord.get(CODE_OFFICIEL_COMMUNE.getLabel()))
                .nomOfficiel(csvRecord.get(NOM_OFFICIEL_COMMUNE.getLabel()))
                .codeOfficielArrondissement(csvRecord.get(CODE_OFFICIEL_ARRONDISSEMENT.getLabel()))
                .nomOfficielArrondissement(csvRecord.get(NOM_OFFICIEL_ARRONDISSEMENT.getLabel()))
                .departement(departement)
                .build();
    }

    private static Departement getDepartementFromRecord(CSVRecord csvRecord, Region region) {
        return new Departement.Builder()
                .codeOfficiel(csvRecord.get(CODE_OFFICIEL_DEPARTEMENT.getLabel()))
                .nomOfficiel(csvRecord.get(NOM_OFFICIEL_DEPARTEMENT.getLabel()))
                .region(region)
                .build();
    }

    private static Region getRegionFromRecord(CSVRecord csvRecord) {
        return new Region.Builder()
                .codeOfficiel(csvRecord.get(CODE_OFFICIEL_REGION.getLabel()))
                .nomOfficiel(csvRecord.get(NOM_OFFICIEL_REGION.getLabel()))
                .build();
    }

    public void setCsvFileUrl(URL csvFileUrl) {
        this.csvFileUrl = csvFileUrl;
    }

}