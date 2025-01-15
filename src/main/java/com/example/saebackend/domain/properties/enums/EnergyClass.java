package com.example.saebackend.domain.properties.enums;

public enum EnergyClass {
    A,
    B,
    C,
    D,
    E,
    F,
    G;

    public static EnergyClass fromString(String energyClass) {
        if(energyClass == null || energyClass.isEmpty())  return null;
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
