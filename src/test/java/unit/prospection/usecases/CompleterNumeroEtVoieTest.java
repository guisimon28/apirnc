package unit.prospection.usecases;

import com.amperus.prospection.adapters.secondary.banprovision.InMemoriesBanDataProviderStub;
import com.amperus.prospection.adapters.secondary.repositories.InMemoriesCoproprieteRepositoryStub;
import com.amperus.prospection.businesslogic.gateways.banprovision.Geolocalisation;
import com.amperus.prospection.businesslogic.models.Adresse;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.usecases.CompleterNumeroEtVoie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CompleterNumeroEtVoieTest {

    private final InMemoriesBanDataProviderStub banDataProviderStub = new InMemoriesBanDataProviderStub();
    private final InMemoriesCoproprieteRepositoryStub coproprieteRepository = new InMemoriesCoproprieteRepositoryStub();

    private final Copropriete copropriete = CoproprieteTestDataFactory.aCoproprieteWithEmptyStreet().build();

    private final String FAKE_STREET = "fake street";

    @BeforeEach
    void setUp() {
        coproprieteRepository.setCoproprietes(copropriete);

        var geolocalisation = new Geolocalisation.Builder()
                .latitude(copropriete.adresse().coordonneesGeographiques().latitude())
                .longitude(copropriete.adresse().coordonneesGeographiques().longitude())
                .build();
        banDataProviderStub.addStreet(geolocalisation, FAKE_STREET);
    }

    @Test
    void should_complete_street_when_handle_ban_complete() {
        new CompleterNumeroEtVoie(banDataProviderStub, coproprieteRepository).handle();

        List<Copropriete> coproprietes = coproprieteRepository.findAll();
        assertThat(coproprietes).hasSize(1)
                .first()
                .extracting(Copropriete::adresse)
                .extracting(Adresse::numeroEtVoie)
                .isEqualTo(FAKE_STREET);
    }

}
