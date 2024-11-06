package com.example.saebackend.model.property.rental;

public enum SubscriptionFrequency {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY;

    public static SubscriptionFrequency fromString(String frequency) {
        return switch (frequency) {
            case "DAILY" -> DAILY;
            case "WEEKLY" -> WEEKLY;
            case "MONTHLY" -> MONTHLY;
            case "YEARLY" -> YEARLY;
            default -> null;
        };
    }

    public static String toString(SubscriptionFrequency frequency) {
        return switch (frequency) {
            case DAILY -> "DAILY";
            case WEEKLY -> "WEEKLY";
            case MONTHLY -> "MONTHLY";
            case YEARLY -> "YEARLY";
        };
    }
}
