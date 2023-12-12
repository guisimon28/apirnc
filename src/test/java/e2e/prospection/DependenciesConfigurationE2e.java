package e2e.prospection;

import com.amperus.prospection.adapters.secondary.rncprovision.CsvFileRncDataProvider;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.gateways.rncDataProvision.RncDataProvider;
import com.amperus.prospection.businesslogic.usecases.ImportRegistreNationalCopropriete;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.URL;

@Configuration
@Profile("prod-e2e")
public class DependenciesConfigurationE2e {
    @Value("${rnc.file.url}")
    private URL rncCsvFileUrl;


    @Bean
    public ImportRegistreNationalCopropriete importRegistreNationalCopropriete(RncDataProvider rncDataProvider, CoproprieteRepository coproprieteRepository) {
        return new ImportRegistreNationalCopropriete(
                rncDataProvider,
                coproprieteRepository
        );
    }

    @Bean
    public RncDataProvider rncDataProvider() {
        var csvFileRncDataProvider = new CsvFileRncDataProvider();
        csvFileRncDataProvider.setCsvFileUrl(rncCsvFileUrl);
        return csvFileRncDataProvider;
    }

}