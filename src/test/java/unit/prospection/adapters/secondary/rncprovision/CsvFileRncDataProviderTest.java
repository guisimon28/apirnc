package unit.prospection.adapters.secondary.rncprovision;

import com.amperus.prospection.adapters.secondary.rncprovision.CsvFileRncDataProvider;
import com.amperus.prospection.businesslogic.models.Adresse;
import com.amperus.prospection.businesslogic.models.Copropriete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CsvFileRncDataProviderTest {

    private final CsvFileRncDataProvider rncDataProvider = new CsvFileRncDataProvider();

    @BeforeEach
    void setUp() {
        URL csvFileUrl = getClass().getClassLoader().getResource("rnc.csv");
        rncDataProvider.setCsvFileUrl(csvFileUrl);
    }

    @Test
    void should_have_valid_data_when_parsing_rnc_csv_file() {
        List<Copropriete> coproprietes = rncDataProvider.findAllCopropriete();
        assertThat(coproprietes).hasSize(8)
                .contains(RncCsvFileCoproprieteTestDataFactory.aCopropriete().build())
                .extracting(Copropriete::adresse)
                .extracting(Adresse::numeroEtVoie)
                .containsExactly("1 Rue des Forges", "15 Rue de Trion", "2 Rue Paul Claudel",
                        "Station Puy Saint Vincent 1600",
                        "le Cannet (06110) 79 Bis de Route de Valbonne",
                        "66150 Arles sur Tech 6 Camp de la Teuleria",
                        "25/27 Rue Chaubert 1046 à 1078 Rue D'ivoy",
                        "35-37-39 Rue Malvoisine");
    }
}
