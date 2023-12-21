package com.amperus.prospection.adapters.secondary.rncprovision;

import org.apache.commons.text.WordUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class AddressFormatterUtils {
    private static final String SEPARATOR = " ";
    private static final String TILDE = "-";
    private static final String COMMA = ",";
    private static final Set<String> NON_CAPITALIZED_WORDS = Set.of(
            "le", "la", "l'", "du", "de", "des", "à", "les", "sur"
    );

    private AddressFormatterUtils() {
    }
    
    /**
     * Formate une adresse en remplaçant les abréviations, en supprimant les virgules,
     * et en appliquant la capitalisation selon les règles spécifiées.
     *
     * @param address L'adresse à formater.
     * @return L'adresse formatée.
     */
    public static String formatAddress(String address) {
        if (address == null) {
            return "";
        }
        return Arrays.stream(address.split(SEPARATOR))
                .map(word -> capitalizeIfNeeded(TypeVoieAbbreviation.unAbbreviateIfNeeded(word)))
                .collect(Collectors.joining(SEPARATOR))
                .replace(COMMA, "")
                .trim();
    }

    private static String capitalizeIfNeeded(String word) {
        return shouldCapitalize(word) ? capitalizeFully(word) : word;
    }

    private static boolean shouldCapitalize(String word) {
        return NON_CAPITALIZED_WORDS.stream().noneMatch(word::equalsIgnoreCase);
    }

    private static String capitalizeFully(String word) {
        if (word.contains(TILDE)) {
            return Arrays.stream(word.split(TILDE))
                    .map(w -> shouldCapitalize(w) ? WordUtils.capitalizeFully(w) : w)
                    .collect(Collectors.joining(TILDE));
        }
        return WordUtils.capitalizeFully(word);
    }
}
