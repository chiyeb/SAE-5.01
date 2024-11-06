package com.example.saebackend.model.property;

public enum PropertyType {
    HOUSE,
    APARTMENT;

    public static PropertyType fromString(String type) {
        return switch (type) {
            case "HOUSE" -> HOUSE;
            case "APARTMENT" -> APARTMENT;
            default -> null;
        };
    }

    public static String toString(PropertyType type) {
        return switch (type) {
            case HOUSE -> "HOUSE";
            case APARTMENT -> "APARTMENT";
        };
    }

    public static boolean isValid(String type) {
        return switch (type) {
            case "HOUSE", "APARTMENT" -> true;
            default -> false;
        };
    }
}
