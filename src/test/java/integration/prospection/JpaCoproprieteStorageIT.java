package integration.prospection;

import com.amperus.prospection.adapters.secondary.repositories.jpa.JpaCoproprieteStorage;
import com.amperus.prospection.adapters.secondary.repositories.jpa.SpringCoproprieteRepository;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.*;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.Mandat;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class JpaCoproprieteStorageIT extends BaseIntegration {

    @Autowired
    private JpaCoproprieteStorage jpaCoproprieteRepository;

    @Autowired
    private SpringCoproprieteRepository springCoproprieteRepository;

    private final RecursiveComparisonConfiguration configCopropriete = RecursiveComparisonConfiguration.builder()
            .withIgnoredFieldsOfTypes(UUID.class, Long.class)
            .build();

    @Test
    void should_have_copropriete_when_persist_new_data() {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteRepository.saveAll(List.of(copropriete));

        List<CoproprieteJpaEntity> coproprietes = springCoproprieteRepository.findAll();
        assertThat(coproprietes)
                .usingRecursiveFieldByFieldElementComparator(configCopropriete)
                .containsExactly(convertToJpa(copropriete));
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
        coproprieteJpaEntity.setVille(new VilleJpaEntity(copropriete.adresse().ville()));
        coproprieteJpaEntity.setMandats(List.of(convertToJpa(copropriete.mandat())));
        return coproprieteJpaEntity;
    }

    private MandatJpaEntity convertToJpa(Mandat mandat) {
        SyndicatJpaEntity syndicatJpaEntity = new SyndicatJpaEntity();
        syndicatJpaEntity.update(mandat.syndicat());
        MandatJpaEntity mandatJpaEntity = new MandatJpaEntity();
        mandatJpaEntity.update(mandat);
        mandatJpaEntity.setSyndicat(syndicatJpaEntity);
        return mandatJpaEntity;
    }
}