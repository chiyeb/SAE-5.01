package com.example.saebackend.repositories;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class InMemoryPropertyRepository implements PropertyRepositoryInterface{

    private final HashMap<Id, Property> properties = new HashMap<>();

    @Override
    public Property create(Property property) {
        properties.put(property.getId(), property);
        return property;
    }

    @Override
    public Property update(Property property) {
        properties.put(property.getId(), property);
        return property;
    }

    @Override
    public Property delete(Id id) {
        return properties.remove(id);
    }

    @Override
    public Property getById(Id id) {
        return properties.get(id);
    }
}
