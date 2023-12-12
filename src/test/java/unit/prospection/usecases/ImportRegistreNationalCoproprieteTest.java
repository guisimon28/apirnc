package unit.prospection.usecases;

import com.amperus.prospection.adapters.secondary.repositories.InMemoriesCoproprieteRepositoryStub;
import com.amperus.prospection.adapters.secondary.rncprovision.InMemoriesRncDataProviderStub;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.usecases.ImportRegistreNationalCopropriete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ImportRegistreNationalCoproprieteTest {

    private final InMemoriesRncDataProviderStub rncDataProvider = new InMemoriesRncDataProviderStub();
    private final InMemoriesCoproprieteRepositoryStub coproprieteRepository = new InMemoriesCoproprieteRepositoryStub();

    private final Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();

    @BeforeEach
    void setUp() {
        rncDataProvider.setCoproprietes(copropriete);
    }

    @Test
    void should_store_new_data_when_handle_rnc_import() {
        new ImportRegistreNationalCopropriete(rncDataProvider, coproprieteRepository).handle();

        List<Copropriete> coproprietes = coproprieteRepository.findAll();
        assertThat(coproprietes).containsExactly(copropriete);
    }

    @Test
    void should_store_update_data_when_handle_rnc_import() {
        var existingCopropriete = CoproprieteTestDataFactory.aCopropriete();
        existingCopropriete.nomUsage("update nom usage");
        existingCopropriete.cadastre(null);
        coproprieteRepository.setCoproprietes(existingCopropriete.build());

        new ImportRegistreNationalCopropriete(rncDataProvider, coproprieteRepository).handle();

        List<Copropriete> coproprietes = coproprieteRepository.findAll();
        assertThat(coproprietes).containsExactly(copropriete);
    }
}
