package unit.prospection.adapters.secondary.rncprovision;

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
        URL csvFileUrl = getClass().getClassLoader().getResource("rnc.csv");
        rncDataProvider.setCsvFileUrl(csvFileUrl);
    }

    @Test
    void should_have_valid_data_when_parsing_rnc_csv_file() {
        List<Copropriete> coproprietes = rncDataProvider.findAllCopropriete();
        assertThat(coproprietes).hasSize(3)
                .contains(RncCsvFileCoproprieteTestDataFactory.aCopropriete().build());
    }
}
