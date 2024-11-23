package com.example.saebackend.domain.id;

import java.util.Objects;

public class Id {

    private final String id;

    private Id(String id) {
        this.id = id;
    }

    public static Id generate() {
        return new Id(java.util.UUID.randomUUID().toString());
    }

    public String toString() {
        return id;
    }

    public static Id fromString(String id) {
        return new Id(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id1 = (Id) o;
        return Objects.equals(id, id1.id);
    }
}
