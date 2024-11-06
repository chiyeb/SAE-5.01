package com.example.saebackend.model.property;

public enum EnergyClass {
    A,
    B,
    C,
    D,
    E,
    F,
    G;

    public static EnergyClass fromString(String energyClass) {
        return switch (energyClass) {
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

    public static String toString(EnergyClass energyClass) {
        return switch (energyClass) {
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
