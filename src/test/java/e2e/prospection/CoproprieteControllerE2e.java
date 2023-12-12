package e2e.prospection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class CoproprieteControllerE2e extends BaseE2e {

    @Autowired
    protected MockMvc mvc;

    @Test
    void should_store_data_when_synchronize_from_rnc_file() throws Exception {
        mvc.perform(post("/coproprietes/import")).andExpect(status().isCreated());
    }
}
