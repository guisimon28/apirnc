package unit.prospection.usecases;

import com.amperus.prospection.adapters.secondary.repositories.InMemoriesCoproprieteRepositoryStub;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.usecases.GetCopropriete;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetCoproprieteTest {

    private final InMemoriesCoproprieteRepositoryStub coproprieteRepository = new InMemoriesCoproprieteRepositoryStub();

    private final Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();

    @Test
    void should_get_copropriete_by_numero_immatriculation_when_search() {
        var existingCopropriete = CoproprieteTestDataFactory.aCopropriete().build();
        coproprieteRepository.setCoproprietes(existingCopropriete);

        var aRetrieveCopropriete = new GetCopropriete(coproprieteRepository).byNumeroImmatriculation(existingCopropriete.numeroImmatriculation());

        assertThat(aRetrieveCopropriete).isPresent().get().isEqualTo(existingCopropriete);
    }

}
