package unit.usecases;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amperus.apirnc.adapters.secondary.repositories.InMemoriesCoproprieteRepositoryStub;
import com.amperus.apirnc.adapters.secondary.rncDataProvision.InMemoriesRncDataProviderStub;
import com.amperus.apirnc.businesslogic.models.Copropriete;
import com.amperus.apirnc.businesslogic.usecases.ImportRegistreNationalCopropriete;

class ImportRegistreNationalCoproprieteTest {

	private final InMemoriesRncDataProviderStub rncDataProvider = new InMemoriesRncDataProviderStub();
	private final InMemoriesCoproprieteRepositoryStub coproprieteRepository = new InMemoriesCoproprieteRepositoryStub();

	private final Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();

	@BeforeEach
	void setUp() {
		rncDataProvider.setCoproprietes(copropriete);
	}

	@Test
	void should_store_new_data_when_handle_rnc_import(){
		new ImportRegistreNationalCopropriete(rncDataProvider, coproprieteRepository).handle(null);

		List<Copropriete> coproprietes = coproprieteRepository.findAll();
		assertThat(coproprietes).containsExactly(copropriete);
	}

	@Test
	void should_store_update_data_when_handle_rnc_import(){
		var existingCopropriete = CoproprieteTestDataFactory.aCopropriete();
		existingCopropriete.nomUsage("update nom usage");
		existingCopropriete.cadastre(null);
		coproprieteRepository.setCoproprietes(existingCopropriete.build());

		new ImportRegistreNationalCopropriete(rncDataProvider, coproprieteRepository).handle(null);

		List<Copropriete> coproprietes = coproprieteRepository.findAll();
		assertThat(coproprietes).containsExactly(copropriete);
	}
}
