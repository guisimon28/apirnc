package integration.prospection;

import com.amperus.prospection.adapters.secondary.repositories.jpa.JpaCoproprieteRepository;
import com.amperus.prospection.adapters.secondary.repositories.jpa.SpringCoproprieteRepository;
import com.amperus.prospection.adapters.secondary.repositories.jpa.mappers.CoproprieteMapper;
import com.amperus.prospection.businesslogic.models.Copropriete;
import integration.BaseIntegration;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class JpaCoproprieteStorageIT extends BaseIntegration {

    @Autowired
    private JpaCoproprieteRepository jpaCoproprieteRepository;

    @Autowired
    private SpringCoproprieteRepository springCoproprieteRepository;

    private final RecursiveComparisonConfiguration config = RecursiveComparisonConfiguration.builder()
            .withIgnoredFieldsOfTypes(UUID.class)
            .build();

    @Test
    void should_have_copropriete_when_persist_new_data() {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        jpaCoproprieteRepository.saveAll(List.of(copropriete));
        assertThat(springCoproprieteRepository.findAll())
                .usingRecursiveFieldByFieldElementComparator(config)
                .containsExactly(CoproprieteMapper.convert(copropriete));
    }
}