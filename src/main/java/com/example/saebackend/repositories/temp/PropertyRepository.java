package com.example.saebackend.repositories.temp;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.Property;
public interface PropertyRepository {

    public Property create(Property property);
    public Property update(Property property);
    public Property delete(Id id);
    public Property getById(Id id);
}
