package com.example.saebackend.data.temp;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.Property;
import com.example.saebackend.repositories.temp.PropertyRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class InMemoryPropertyRepository implements PropertyRepository {

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
