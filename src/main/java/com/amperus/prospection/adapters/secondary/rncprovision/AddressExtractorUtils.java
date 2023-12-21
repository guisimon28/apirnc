package com.amperus.prospection.adapters.secondary.rncprovision;

import com.amperus.prospection.businesslogic.models.Adresse;
import com.amperus.prospection.businesslogic.models.Ville;

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
        Pattern pattern = Pattern.compile("(?<street>.*?)(?<postalCode>\\d{5})(?<city>.*)");
        Matcher matcher = pattern.matcher(formattedAddress);

        var builder = new Adresse.Builder();
        if (matcher.find()) {
            builder.numeroEtVoie(matcher.group("street").trim())
                    .codePostal(matcher.group("postalCode").trim());
            var villeBuilder = new Ville.Builder()
                    .codePostal(matcher.group("postalCode").trim())
                    .nom(matcher.group("city").trim());
            builder.ville(villeBuilder.build());
        }
        return builder.build();
    }
}
