package com.example.saebackend.repositories;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
import org.springframework.stereotype.Repository;

@Repository
public class PropertyRepository implements PropertyRepositoryInterface {

    @Override
    public void create(Property property) {

    }

    @Override
    public void update(Id id, Property property) {

    }

    @Override
    public void delete(Id id) {

    }

    @Override
    public Property getById(Id id) {
        return null;
    }
}
