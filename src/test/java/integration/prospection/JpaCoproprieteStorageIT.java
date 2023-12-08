package integration.prospection;

import com.amperus.prospection.adapters.secondary.repositories.jpa.JpaCoproprieteRepository;
import com.amperus.prospection.adapters.secondary.repositories.jpa.SpringCoproprieteRepository;
import integration.BaseIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JpaCoproprieteStorageIT extends BaseIntegration {

    @Autowired
    private JpaCoproprieteRepository jpaCoproprieteRepository;

    @Autowired
    private SpringCoproprieteRepository springCoproprieteRepository;

    @Test
    void should_have_copropriete_when_persist_new_data() {

        jpaCoproprieteRepository.saveAll(List.of(CoproprieteTestDataFactory.aCopropriete().build()));

        assertThat(springCoproprieteRepository.findAll()).isNotEmpty();
    }
}