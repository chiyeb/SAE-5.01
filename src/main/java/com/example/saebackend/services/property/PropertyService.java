package com.example.saebackend.services.property;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.Property;
import com.example.saebackend.data.InMemoryPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PropertyService implements PropertyServiceInterface{
    private final InMemoryPropertyRepository InMemoryPropertyR;

    @Autowired
    public PropertyService(InMemoryPropertyRepository InMemoryPropertyR) {
        this.InMemoryPropertyR = InMemoryPropertyR;
    }

    @Override
    public Object create(Property property) {
        return InMemoryPropertyR.create(property);
    }

    @Override
    public Object update(Property property) {
        return InMemoryPropertyR.update(property);
    }

    @Override
    public Object delete(Id id) {
        return InMemoryPropertyR.delete(id);
    }

    @Override
    public Property getById(Id id) {
        return null;
    }

    @Override
    public ArrayList<Property> getAll() {
        return null;
    }
}