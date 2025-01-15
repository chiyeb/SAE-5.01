package com.example.saebackend.domain.properties.enums;

public enum PropertyType {
    HOUSE,
    APARTMENT;

    public static PropertyType fromString(String type) {
        if(type == null || type.isEmpty())  return null;
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
