package integration.prospection.repositories;

import com.amperus.prospection.adapters.secondary.repositories.jpa.JpaCoproprieteStorage;
import com.amperus.prospection.adapters.secondary.repositories.jpa.SpringCoproprieteRepository;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.*;
import com.amperus.prospection.businesslogic.models.*;
import integration.prospection.BaseIntegration;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

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