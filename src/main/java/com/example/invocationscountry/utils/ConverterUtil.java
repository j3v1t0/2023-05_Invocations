package com.example.invocationscountry.utils;

public class ConverterUtil {
    public static double convertStringToDouble(String convertString) {
        if (convertString == null) {
            throw new IllegalArgumentException("Input String cannot be null");
        }
        try {
            return Double.parseDouble(convertString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input String is not a valid double", e);
        }
    }
    public static String convertDoubleToString(Double covertDouble) {
        if (covertDouble == null) {
            return null;
        }
        return String.format("%.2f km", covertDouble);
    }
}
