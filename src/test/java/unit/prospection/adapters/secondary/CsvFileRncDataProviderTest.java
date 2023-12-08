package unit.prospection.adapters.secondary;

import com.amperus.prospection.adapters.secondary.rncprovision.CsvFileRncDataProvider;
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
        // https://www.data.gouv.fr/fr/datasets/r/3ea8e2c3-0038-464a-b17e-cd5c91f65ce2
        URL csvFileUrl = getClass().getClassLoader().getResource("rnc.csv");
        rncDataProvider.setCsvFileUrl(csvFileUrl);
    }

    @Test
    void should_have_valid_data_when_parsing_rnc_csv_file() {
        List<Copropriete> coproprietes = rncDataProvider.findAllCopropriete();
        assertThat(coproprietes).contains(RncCsvFileCoproprieteTestDataFactory.aCopropriete().build());
    }
}
