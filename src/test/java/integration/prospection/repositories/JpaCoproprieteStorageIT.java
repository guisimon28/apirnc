package integration.prospection.repositories;

import com.amperus.prospection.adapters.secondary.repositories.jpa.JpaCoproprieteStorage;
import com.amperus.prospection.adapters.secondary.repositories.jpa.SpringCoproprieteRepository;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.*;
import com.amperus.prospection.businesslogic.models.*;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPage;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPageable;
import com.amperus.prospection.businesslogic.models.pagination.MyAppSort;
import integration.prospection.BaseIntegration;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static com.amperus.prospection.businesslogic.models.pagination.MyAppSortDirection.DSC;
import static org.assertj.core.api.Assertions.assertThat;

class JpaCoproprieteStorageIT extends BaseIntegration {

    @Autowired
    private JpaCoproprieteStorage jpaCoproprieteStorage;

    @Autowired
    private SpringCoproprieteRepository springCoproprieteRepository;

    private final RecursiveComparisonConfiguration configCopropriete = RecursiveComparisonConfiguration.builder()
            .withIgnoredFieldsOfTypes(UUID.class, Long.class)
            .build();

    @Test
    void should_have_copropriete_when_persist_new_data() {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(copropriete));

        List<CoproprieteJpaEntity> coproprietes = springCoproprieteRepository.findAll();
        assertThat(coproprietes)
                .usingRecursiveFieldByFieldElementComparator(configCopropriete)
                .containsExactly(convertToJpa(copropriete));
    }

    @Test
    void should_find_by_numero_immatriculation_when_search() {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(copropriete));

        assertThat(jpaCoproprieteStorage.findByNumeroImmatriculation(copropriete.numeroImmatriculation()))
                .isPresent().get().usingRecursiveComparison(configCopropriete)
                .isEqualTo(copropriete);
    }

    @Test
    void should_find_all_coproprietes_when_search_with_pagination() {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(copropriete));

        assertThat(jpaCoproprieteStorage.findAllCoproprietes(new MyAppPageable.Builder().page(1).pageSize(10).build())).usingRecursiveComparison(configCopropriete)
                .isEqualTo(MyAppPage.builder(List.of(copropriete)).currentPage(1).pageSize(10).build());
    }

    @Test
    void should_find_only_by_nomUsage_when_search_with_pagination() {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(copropriete));

        var myAppPageable = new MyAppPageable.Builder().page(1).pageSize(10).searchTerm(copropriete.nomUsage().substring(2)).build();
        assertThat(jpaCoproprieteStorage.findAllCoproprietes(myAppPageable)).usingRecursiveComparison(configCopropriete)
                .isEqualTo(MyAppPage.builder(List.of(copropriete)).currentPage(1).pageSize(10).build());
    }

    @Test
    void should_find_only_by_numeroImmatriculation_when_search_with_pagination() {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(copropriete));

        var myAppPageable = new MyAppPageable.Builder().page(1).pageSize(10).searchTerm(copropriete.numeroImmatriculation().substring(2)).build();
        assertThat(jpaCoproprieteStorage.findAllCoproprietes(myAppPageable)).usingRecursiveComparison(configCopropriete)
                .isEqualTo(MyAppPage.builder(List.of(copropriete)).currentPage(1).pageSize(10).build());
    }

    @Test
    void should_find_only_by_numeroEtVoie_when_search_with_pagination() {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(copropriete));

        var myAppPageable = new MyAppPageable.Builder().page(1).pageSize(10).searchTerm(copropriete.adresse().numeroEtVoie().toUpperCase().substring(2)).build();
        assertThat(jpaCoproprieteStorage.findAllCoproprietes(myAppPageable)).usingRecursiveComparison(configCopropriete)
                .isEqualTo(MyAppPage.builder(List.of(copropriete)).currentPage(1).pageSize(10).build());
    }

    @Test
    void should_find_only_by_ville_when_search_with_pagination() {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(copropriete));

        var myAppPageable = new MyAppPageable.Builder().page(1).pageSize(10).searchTerm(copropriete.adresse().ville().nom().toUpperCase().substring(2)).build();
        assertThat(jpaCoproprieteStorage.findAllCoproprietes(myAppPageable)).usingRecursiveComparison(configCopropriete)
                .isEqualTo(MyAppPage.builder(List.of(copropriete)).currentPage(1).pageSize(10).build());
    }

    @Test
    void should_find_only_by_syndicat_when_search_with_pagination() {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(copropriete));

        var myAppPageable = new MyAppPageable.Builder().page(1).pageSize(10).searchTerm(copropriete.mandat().syndicat().raisonSociale().toUpperCase().substring(2)).build();
        assertThat(jpaCoproprieteStorage.findAllCoproprietes(myAppPageable)).usingRecursiveComparison(configCopropriete)
                .isEqualTo(MyAppPage.builder(List.of(copropriete)).currentPage(1).pageSize(10).build());
    }

    @Test
    void should_sort_by_nomUsage_when_search_with_pagination_and_sorting() {
        Copropriete aCopropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(aCopropriete));
        Copropriete anotherCopropriete = CoproprieteTestDataFactory.aCoproprieteWithAnotherImmatriculation().build();
        jpaCoproprieteStorage.saveAll(List.of(anotherCopropriete));

        executeSortingTest("nomUsage", Copropriete::nomUsage, anotherCopropriete.nomUsage(), aCopropriete.nomUsage());
    }

    @Test
    void should_sort_by_numeroImmatriculation_when_search_with_pagination_and_sorting() {
        Copropriete aCopropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(aCopropriete));
        Copropriete anotherCopropriete = CoproprieteTestDataFactory.aCoproprieteWithAnotherImmatriculation().build();
        jpaCoproprieteStorage.saveAll(List.of(anotherCopropriete));

        executeSortingTest("numeroImmatriculation", Copropriete::numeroImmatriculation, anotherCopropriete.numeroImmatriculation(), aCopropriete.numeroImmatriculation());
    }

    @Test
    void should_sort_by_numeroEtVoie_when_search_with_pagination_and_sorting() {
        Copropriete aCopropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(aCopropriete));
        Copropriete anotherCopropriete = CoproprieteTestDataFactory.aCoproprieteWithAnotherImmatriculation().build();
        jpaCoproprieteStorage.saveAll(List.of(anotherCopropriete));

        executeSortingTest("adresse", copropriete -> copropriete.adresse().numeroEtVoie(), anotherCopropriete.adresse().numeroEtVoie(),
                aCopropriete.adresse().numeroEtVoie());
    }

    @Test
    void should_sort_by_ville_when_search_with_pagination_and_sorting() {
        Copropriete aCopropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(aCopropriete));
        Copropriete anotherCopropriete = CoproprieteTestDataFactory.aCoproprieteWithAnotherImmatriculation().build();
        jpaCoproprieteStorage.saveAll(List.of(anotherCopropriete));

        executeSortingTest("ville", copropriete -> copropriete.adresse().ville().nom(), anotherCopropriete.adresse().ville().nom(),
                aCopropriete.adresse().ville().nom());
    }


    @Test
    void should_sort_by_stationnement_when_search_with_pagination_and_sorting() {
        Copropriete aCopropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(aCopropriete));
        Copropriete anotherCopropriete = CoproprieteTestDataFactory.aCoproprieteWithAnotherImmatriculation().build();
        jpaCoproprieteStorage.saveAll(List.of(anotherCopropriete));

        executeSortingTest("nombreStationnement", copropriete -> copropriete.lots().nombreStationnement(), anotherCopropriete.lots().nombreStationnement(),
                aCopropriete.lots().nombreStationnement());
    }

    @Test
    void should_sort_by_syndicat_when_search_with_pagination_and_sorting() {
        Copropriete aCopropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteStorage.saveAll(List.of(aCopropriete));
        Copropriete anotherCopropriete = CoproprieteTestDataFactory.aCoproprieteWithAnotherImmatriculation().build();
        jpaCoproprieteStorage.saveAll(List.of(anotherCopropriete));

        executeSortingTest("syndicat", copropriete -> copropriete.mandat().syndicat().raisonSociale(), anotherCopropriete.mandat().syndicat().raisonSociale(),
                aCopropriete.mandat().syndicat().raisonSociale());
    }

    private void executeSortingTest(String sortPath, Function<Copropriete, Comparable<?>> extractor, Comparable<?>... expectedOrder) {
        var myAppPageable = new MyAppPageable.Builder()
                .page(1)
                .pageSize(10)
                .sort(new MyAppSort.Builder().path(sortPath).direction(DSC).build())
                .build();

        MyAppPage<Copropriete> resultPage = jpaCoproprieteStorage.findAllCoproprietes(myAppPageable);

        assertThat(resultPage.getContent())
                .extracting(extractor)
                .containsExactly(expectedOrder);
    }


    private CoproprieteJpaEntity convertToJpa(Copropriete copropriete) {
        CoproprieteJpaEntity coproprieteJpaEntity = new CoproprieteJpaEntity();
        coproprieteJpaEntity.update(copropriete);

        List<InformationCadastraleJpaEntity> informationCadastrales = copropriete.informationsCadastrales().stream().map(info -> {
            InformationCadastraleJpaEntity informationCadastrale = new InformationCadastraleJpaEntity();
            informationCadastrale.update(info);
            return informationCadastrale;
        }).toList();
        coproprieteJpaEntity.setInformationsCadastrales(informationCadastrales);
        coproprieteJpaEntity.setVille(convertToJpa(copropriete.adresse().ville()));
        coproprieteJpaEntity.setMandats(List.of(convertToJpa(copropriete.mandat(), coproprieteJpaEntity)));
        return coproprieteJpaEntity;
    }

    private VilleJpaEntity convertToJpa(Ville ville) {
        VilleJpaEntity villeJpaEntity = new VilleJpaEntity();
        villeJpaEntity.update(ville);
        villeJpaEntity.setDepartement(convertToJpa(ville.departement()));
        return villeJpaEntity;
    }

    private DepartementJpaEntity convertToJpa(Departement departement) {
        DepartementJpaEntity departementJpaEntity = new DepartementJpaEntity();
        departementJpaEntity.update(departement);
        departementJpaEntity.setRegion(convertToJpa(departement.region()));
        return departementJpaEntity;
    }

    private RegionJpaEntity convertToJpa(Region region) {
        RegionJpaEntity regionJpaEntity = new RegionJpaEntity();
        regionJpaEntity.update(region);
        return regionJpaEntity;
    }

    private MandatJpaEntity convertToJpa(Mandat mandat, CoproprieteJpaEntity coproprieteJpaEntity) {
        SyndicatJpaEntity syndicatJpaEntity = new SyndicatJpaEntity();
        syndicatJpaEntity.update(mandat.syndicat());
        MandatJpaEntity mandatJpaEntity = new MandatJpaEntity();
        mandatJpaEntity.update(mandat);
        mandatJpaEntity.setSyndicat(syndicatJpaEntity);
        mandatJpaEntity.setCopropriete(coproprieteJpaEntity);
        return mandatJpaEntity;
    }
}