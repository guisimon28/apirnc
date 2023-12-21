package e2e.prospection;

import com.amperus.prospection.adapters.secondary.repositories.jpa.SpringCoproprieteRepository;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.AdresseJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.CoproprieteJpaEntity;
import com.amperus.prospection.businesslogic.models.Copropriete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    void should_complete_missing_street_when_import() throws Exception {
        Copropriete copropriete = CoproprieteTestDataFactory.aCoproprieteWithEmptyStreet().build();
        CoproprieteJpaEntity coproprieteJpaEntity = new CoproprieteJpaEntity();
        coproprieteJpaEntity.update(copropriete);
        springCoproprieteRepository.save(coproprieteJpaEntity);

        mvc.perform(post("/coproprietes/completeMissingStreet")).andExpect(status().isCreated());

        assertThat(springCoproprieteRepository.findAll())
                .first()
                .extracting(CoproprieteJpaEntity::getAdresse)
                .extracting(AdresseJpaEntity::getNumeroEtVoieGpx)
                .isNotNull();
    }

    @Test
    void should_find_copropriete_when_search_by_numeroImmatriculation() throws Exception {
        Copropriete copropriete = CoproprieteTestDataFactory.aCopropriete().build();
        CoproprieteJpaEntity coproprieteJpaEntity = new CoproprieteJpaEntity();
        coproprieteJpaEntity.update(copropriete);
        springCoproprieteRepository.save(coproprieteJpaEntity);

        ResultActions resultActions = mvc.perform(get("/coproprietes/find/" + copropriete.numeroImmatriculation()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
