package unit.prospection.adapters.secondary.rncprovision;

import com.amperus.prospection.adapters.secondary.rncprovision.AddressExtractorUtils;
import com.amperus.prospection.adapters.secondary.rncprovision.AddressFormatterUtils;
import com.amperus.prospection.businesslogic.models.Adresse;
import com.amperus.prospection.businesslogic.models.Ville;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AddressFormatterTest {
    @ParameterizedTest
    @MethodSource("addressProvider")
    void should_transform_address_at_comprehensive_format(String input, String expected) {
        assertThat(AddressFormatterUtils.formatAddress(input)).isEqualTo(expected);
    }

    private static Stream<Arguments> addressProvider() {
        return Stream.of(
                Arguments.of("31 r de village neuf", "31 Rue de Village Neuf"),
                Arguments.of("2 av des Champs", "2 Avenue des Champs"),
                Arguments.of("10 bd de la République", "10 Boulevard de la République"),
                Arguments.of("5 imp des Tulipes", "5 Impasse des Tulipes"),
                Arguments.of("50 rte de la Plage", "50 Route de la Plage"),
                Arguments.of("3 pl de l'Église", "3 Place de L'église"),
                Arguments.of("7 all des Acacias", "7 Allée des Acacias"),
                Arguments.of("100 crs Napoléon", "100 Cours Napoléon"),
                Arguments.of("15 che du Moulin", "15 Chemin du Moulin"),
                Arguments.of("17 res des Lys", "17 Résidence des Lys"),
                Arguments.of("12 sen de la Forêt", "12 Sentier de la Forêt"),
                Arguments.of("5 fg Saint-Antoine", "5 Faubourg Saint-Antoine"),
                Arguments.of("8 chs de la Liberté", "8 Chaussée de la Liberté"),
                Arguments.of("30 ccal des Peupliers", "30 Centre Commercial des Peupliers"),
                Arguments.of("25 cite Jardin", "25 Cité Jardin"),
                Arguments.of("4 clos des Roses", "4 Clos des Roses"),
                Arguments.of("9 col des Alpes", "9 Col des Alpes"),
                Arguments.of("2 cor Georges Pompidou", "2 Corniche Georges Pompidou"),
                Arguments.of("18 rte des Vignes", "18 Route des Vignes"),
                Arguments.of("34 r du marechal de lattre de tassigny", "34 Rue du Marechal de Lattre de Tassigny"),
                Arguments.of("1 imp saint-germain", "1 Impasse Saint-Germain")
        );
    }

    @ParameterizedTest
    @MethodSource("fullAddressProvider")
    void should_extract_address_at_comprehensive_format(String input, Adresse expected) {
        assertThat(AddressExtractorUtils.extractAddress(input)).isEqualTo(expected);
    }

    private static Stream<Arguments> fullAddressProvider() {
        return Stream.of(
                Arguments.of("18 r saint-gabriel 13014 Marseille", convertToAddress("18 Rue Saint-Gabriel", "13014", "Marseille")),
                Arguments.of("r du couvent, 40480 Vieux-Boucau-les-Bains", convertToAddress("Rue du Couvent", "40480", "Vieux-Boucau-les-Bains")),
                Arguments.of("32 RUE MAURICE BERNARD", convertToAddress(null, null, null))
        );
    }

    private static Adresse convertToAddress(String numeroEtVoie, String codePostal, String ville) {
        var builder = new Adresse.Builder();
        builder.numeroEtVoie(numeroEtVoie)
                .codePostal(codePostal);
        if (ville != null) {
            var villeBuilder = new Ville.Builder()
                    .codePostal(codePostal)
                    .nom(ville);
            builder.ville(villeBuilder.build());
        }
        return builder.build();
    }

}