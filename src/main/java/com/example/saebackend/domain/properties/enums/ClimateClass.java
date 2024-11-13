package com.example.saebackend.domain.properties.enums;

public enum ClimateClass {

    A,
    B,
    C,
    D,
    E,
    F,
    G;

    public static ClimateClass fromString(String climateClass) {
        return switch (climateClass) {
            case "A" -> A;
            case "B" -> B;
            case "C" -> C;
            case "D" -> D;
            case "E" -> E;
            case "F" -> F;
            case "G" -> G;
            default -> null;
        };
    }

    public static String toString(ClimateClass climateClass) {
        return switch (climateClass) {
            case A -> "A";
            case B -> "B";
            case C -> "C";
            case D -> "D";
            case E -> "E";
            case F -> "F";
            case G -> "G";
        };
    }

}
