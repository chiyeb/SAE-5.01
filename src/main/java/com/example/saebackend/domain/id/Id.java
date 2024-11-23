package com.example.saebackend.domain.id;

import java.util.Objects;

/**
 * Represents a unique identifier for domain entities.
 * The identifier is internally stored as a String and uses UUID for generation.
 */
public class Id {

    private final String id;

    /**
     * Private constructor to create an `Id` from a given string.
     *
     * @param id The string representation of the ID.
     */
    private Id(String id) {
        this.id = id;
    }

    /**
     * Generates a new unique identifier using UUID.
     *
     * @return A new instance of `Id` with a randomly generated UUID.
     */
    public static Id generate() {
        return new Id(java.util.UUID.randomUUID().toString());
    }

    /**
     * Converts the `Id` object to its string representation.
     *
     * @return The string representation of the ID.
     */
    @Override
    public String toString() {
        return id;
    }

    /**
     * Creates an `Id` object from an existing string.
     *
     * @param id The string representation of the ID.
     * @return A new instance of `Id` based on the given string.
     */
    public static Id fromString(String id) {
        return new Id(id);
    }

    /**
     * Checks if this `Id` object is equal to another object.
     * Two `Id` objects are considered equal if their string representations are the same.
     *
     * @param o The object to compare with.
     * @return `true` if the objects are equal, otherwise `false`.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id1 = (Id) o;
        return Objects.equals(id, id1.id);
    }

    /**
     * Generates a hash code for the `Id` object.
     *
     * @return The hash code based on the string representation of the ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
