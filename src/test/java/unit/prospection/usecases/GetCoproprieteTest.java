package unit.prospection.usecases;

import com.amperus.prospection.adapters.secondary.repositories.InMemoriesCoproprieteRepositoryStub;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPage;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPageable;
import com.amperus.prospection.businesslogic.usecases.GetCopropriete;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static unit.prospection.usecases.CoproprieteTestDataFactory.aCopropriete;
import static unit.prospection.usecases.CoproprieteTestDataFactory.aCoproprieteWithAnotherImmatriculation;

class GetCoproprieteTest {

    private final InMemoriesCoproprieteRepositoryStub coproprieteRepository = new InMemoriesCoproprieteRepositoryStub();

    private final Copropriete copropriete = aCopropriete().build();

    @Test
    void should_get_copropriete_by_numero_immatriculation_when_search() {
        var existingCopropriete = aCopropriete().build();
        coproprieteRepository.setCoproprietes(existingCopropriete);

        var aRetrieveCopropriete = new GetCopropriete(coproprieteRepository).byNumeroImmatriculation(existingCopropriete.numeroImmatriculation());

        assertThat(aRetrieveCopropriete).isPresent().get().isEqualTo(existingCopropriete);
    }

    @Test
    void should_find_all_coproprietes_when_search_with_pagination() {
        var existingCopropriete = aCopropriete().build();
        coproprieteRepository.setCoproprietes(existingCopropriete);

        var aPage = new GetCopropriete(coproprieteRepository).findAllCoproprietes(new MyAppPageable.Builder().page(1).pageSize(10).build());

        assertThat(aPage).usingRecursiveComparison().isEqualTo(MyAppPage.builder(List.of(existingCopropriete)).currentPage(1).pageSize(10).build());
    }

    @Test
    void should_find_copropriete_by_numeroImmatriculation_when_search_with_pagination() {
        var expectedCopropriete = aCopropriete().build();
        coproprieteRepository.setCoproprietes(expectedCopropriete, aCoproprieteWithAnotherImmatriculation().build());

        var myAppPageable = new MyAppPageable.Builder().page(1).pageSize(10).searchTerm(expectedCopropriete.numeroImmatriculation().substring(2)).build();
        var aPage = new GetCopropriete(coproprieteRepository).findAllCoproprietes(myAppPageable);

        assertThat(aPage).usingRecursiveComparison().isEqualTo(MyAppPage.builder(List.of(expectedCopropriete)).currentPage(1).pageSize(10).build());
    }

    @Test
    void should_find_copropriete_by_nomUsage_when_search_with_pagination() {
        var expectedCopropriete = aCopropriete().build();
        coproprieteRepository.setCoproprietes(expectedCopropriete, aCoproprieteWithAnotherImmatriculation().build());

        var myAppPageable = new MyAppPageable.Builder().page(1).pageSize(10).searchTerm(expectedCopropriete.nomUsage().substring(2)).build();
        var aPage = new GetCopropriete(coproprieteRepository).findAllCoproprietes(myAppPageable);

        assertThat(aPage).usingRecursiveComparison().isEqualTo(MyAppPage.builder(List.of(expectedCopropriete)).currentPage(1).pageSize(10).build());
    }

    @Test
    void should_find_copropriete_by_numeroEtVoie_when_search_with_pagination() {
        var expectedCopropriete = aCopropriete().build();
        coproprieteRepository.setCoproprietes(expectedCopropriete, aCoproprieteWithAnotherImmatriculation().build());

        var myAppPageable = new MyAppPageable.Builder().page(1).pageSize(10).searchTerm(expectedCopropriete.adresse().numeroEtVoie().substring(2)).build();
        var aPage = new GetCopropriete(coproprieteRepository).findAllCoproprietes(myAppPageable);

        assertThat(aPage).usingRecursiveComparison().isEqualTo(MyAppPage.builder(List.of(expectedCopropriete)).currentPage(1).pageSize(10).totalElementsCount(1).build());
    }

    @Test
    void should_find_copropriete_by_ville_when_search_with_pagination() {
        var expectedCopropriete = aCopropriete().build();
        coproprieteRepository.setCoproprietes(expectedCopropriete, aCoproprieteWithAnotherImmatriculation().build());

        var myAppPageable = new MyAppPageable.Builder().page(1).pageSize(10).searchTerm(expectedCopropriete.adresse().ville().nom().substring(2)).build();
        var aPage = new GetCopropriete(coproprieteRepository).findAllCoproprietes(myAppPageable);

        assertThat(aPage).usingRecursiveComparison().isEqualTo(MyAppPage.builder(List.of(expectedCopropriete)).currentPage(1).pageSize(10).totalElementsCount(1).build());
    }

    @Test
    void should_find_copropriete_by_syndicat_when_search_with_pagination() {
        var expectedCopropriete = aCopropriete().build();
        coproprieteRepository.setCoproprietes(expectedCopropriete, aCoproprieteWithAnotherImmatriculation().build());

        var myAppPageable = new MyAppPageable.Builder().page(1).pageSize(10).searchTerm(expectedCopropriete.mandat().syndicat().raisonSociale().substring(2)).build();
        var aPage = new GetCopropriete(coproprieteRepository).findAllCoproprietes(myAppPageable);

        assertThat(aPage).usingRecursiveComparison().isEqualTo(MyAppPage.builder(List.of(expectedCopropriete)).currentPage(1).pageSize(10).totalElementsCount(1).build());
    }

}
