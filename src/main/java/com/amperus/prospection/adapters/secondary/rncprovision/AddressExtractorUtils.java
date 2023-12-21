package com.amperus.prospection.adapters.secondary.rncprovision;

import com.amperus.prospection.businesslogic.models.Adresse;
import com.amperus.prospection.businesslogic.models.Ville;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.amperus.prospection.adapters.secondary.rncprovision.AddressFormatterUtils.formatAddress;

public class AddressExtractorUtils {

    private AddressExtractorUtils() {
    }

    /**
     * Extrait une adresse à partir d'une chaîne de caractères.
     *
     * @param address l'adresse à analyser.
     * @return un objet Adresse contenant les informations extraites.
     */
    public static Adresse extractAddress(String address) {
        var formattedAddress = formatAddress(address);
        Pattern pattern = Pattern.compile("(?<street>.*?)(?<postalCode>\\d{4,5})(?<city>.*)");
        Matcher matcher = pattern.matcher(formattedAddress);

        var builder = new Adresse.Builder();
        if (matcher.find()) {
            builder.numeroEtVoie(matcher.group("street").trim());
            var postalCode = matcher.group("postalCode").trim();
            var city = matcher.group("city").trim();
            completeBuilderByPostCodeAndCity(postalCode, city, builder);
        } else {
            builder.numeroEtVoie(formattedAddress);
        }
        return builder.build();
    }

    private static void completeBuilderByPostCodeAndCity(String postalCode, String city, Adresse.Builder builder) {
        if (StringUtils.isNoneBlank(postalCode, city)) {
            var postCodeFormated = reformatPostCode(postalCode);
            builder.codePostal(postCodeFormated);
            var villeBuilder = new Ville.Builder()
                    .codePostal(postCodeFormated)
                    .nom(city);
            builder.ville(villeBuilder.build());
        }
    }

    private static String reformatPostCode(String postalCode) {
        if (postalCode.length() == 4) {
            return "0" + postalCode;
        }
        return postalCode;
    }
}
