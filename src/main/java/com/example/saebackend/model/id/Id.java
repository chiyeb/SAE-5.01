package com.example.saebackend.model.id;

import java.util.Objects;

public class Id {

    private final String id;

    public Id(String id) {
        this.id = id;
    }

    public Id(){
        this.id = java.util.UUID.randomUUID().toString();
    }

    public static Id generate() {
        return new Id(java.util.UUID.randomUUID().toString());
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
