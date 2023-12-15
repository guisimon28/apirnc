package unit.prospection.adapters.secondary.banprovision;

import com.amperus.prospection.adapters.secondary.banprovision.RealBanDataProvider;
import com.amperus.prospection.businesslogic.gateways.banprovision.BanDataProvider;
import com.amperus.prospection.businesslogic.gateways.banprovision.Geolocalisation;
import integration.prospection.BaseIntegration;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class BanProviderTest extends BaseIntegration {

    @Test
    void should_have_street_for_geolocalosation_when_query_ban_api() {

        var geolocalisation = new Geolocalisation.Builder()
                .latitude(43.27115d)
                .longitude(6.638165)
                .build();

        BanDataProvider banDataProvider = new RealBanDataProvider("https://api-adresse.data.gouv.fr/reverse/?");

        Optional<String> numeroEtVoie = banDataProvider.findNumeroEtVoie(geolocalisation);
        assertThat(numeroEtVoie).isNotEmpty().get().isEqualTo("18 Rue du General Allard");

    }

}