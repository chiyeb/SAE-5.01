package com.example.saebackend.services.property;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
import com.example.saebackend.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.saebackend.model.error.Error;

import java.util.ArrayList;

@Service
public class PropertyService implements PropertyServiceInterface{
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Object create(Property property) {
        propertyRepository.create(property);
    }

    @Override
    public Object update(Id id, Property property) {

    }

    @Override
    public Object delete(Id id) {

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