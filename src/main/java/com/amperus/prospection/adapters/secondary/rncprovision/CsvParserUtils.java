package com.amperus.prospection.adapters.secondary.rncprovision;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

class CsvParserUtils {

    private CsvParserUtils() {
    }

    public static boolean parseBoolean(String value) {
        if (value == null) {
            return false;
        }
        return value.equalsIgnoreCase("oui") || value.equalsIgnoreCase("true");
    }

    public static double parseDouble(String value) {
        if (value == null || value.equalsIgnoreCase("nan")) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static int parseInt(String value) {
        if (value == null || value.equalsIgnoreCase("nan")) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static Optional<LocalDate> parseLocalDate(String value, String pattern) {
        if (value == null || value.equalsIgnoreCase("na")) {
            return Optional.empty();
        }
        return Optional.of(LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern)));
    }

}
