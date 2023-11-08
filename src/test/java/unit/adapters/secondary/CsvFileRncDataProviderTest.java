package unit.adapters.secondary;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amperus.apirnc.adapters.secondary.rncDataProvision.CsvFileRncDataProvider;
import com.amperus.apirnc.businesslogic.models.Copropriete;

class CsvFileRncDataProviderTest {

	private final CsvFileRncDataProvider rncDataProvider = new CsvFileRncDataProvider();

	@BeforeEach
	void setUp() {
		// https://www.data.gouv.fr/fr/datasets/r/3ea8e2c3-0038-464a-b17e-cd5c91f65ce2
		URL csvFileUrl = getClass().getClassLoader().getResource("rnc.csv");
		rncDataProvider.setCsvFileUrl(csvFileUrl);
	}

	@Test
	void should_have_valid_data_when_parsing_rnc_csv_file(){
		List<Copropriete> coproprietes = rncDataProvider.findAllCopropriete();
		assertThat(coproprietes).contains(RncCsvFileCoproprieteTestDataFactory.aCopropriete().build());
	}
}
