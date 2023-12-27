package e2e.prospection;

import com.amperus.prospection.adapters.secondary.repositories.jpa.SpringCoproprieteRepository;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.CoproprieteJpaEntity;
import com.amperus.prospection.businesslogic.models.Copropriete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class CoproprieteControllerE2e extends BaseE2e {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    private SpringCoproprieteRepository springCoproprieteRepository;

    @Test
    void should_store_data_when_synchronize_from_rnc_file() throws Exception {
        mvc.perform(post("/coproprietes/import")).andExpect(status().isCreated());
        assertThat(springCoproprieteRepository.findAll()).isNotEmpty();
    }

    @Test
    void should_find_copropriete_when_search_by_numeroImmatriculation() throws Exception {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        CoproprieteJpaEntity coproprieteJpaEntity = new CoproprieteJpaEntity();
        coproprieteJpaEntity.update(copropriete);
        springCoproprieteRepository.save(coproprieteJpaEntity);

        mvc.perform(get("/coproprietes/find/" + copropriete.numeroImmatriculation()))
                .andExpect(status().isOk());
    }

    @Test
    void should_find_all_coproprietes_when_search_with_pagination() throws Exception {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        CoproprieteJpaEntity coproprieteJpaEntity = new CoproprieteJpaEntity();
        coproprieteJpaEntity.update(copropriete);
        springCoproprieteRepository.save(coproprieteJpaEntity);

        mvc.perform(get("/coproprietes/find")
                        .param("sort.path", "nomUsage")
                        .param("sort.direction", "ASC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].numeroImmatriculation").value(copropriete.numeroImmatriculation()));
    }
}
