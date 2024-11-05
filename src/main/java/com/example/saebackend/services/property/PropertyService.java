package com.example.saebackend.services.property;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
import com.example.saebackend.repositories.MariaDBPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PropertyService implements PropertyServiceInterface{
    private final MariaDBPropertyRepository mariaDBPropertyRepository;

    @Autowired
    public PropertyService(MariaDBPropertyRepository mariaDBPropertyRepository) {
        this.mariaDBPropertyRepository = mariaDBPropertyRepository;
    }

    @Override
    public Object create(Property property) {
        return mariaDBPropertyRepository.create(property);
    }

    @Override
    public Object update(Property property) {
        return mariaDBPropertyRepository.update(property);
    }

    @Override
    public Object delete(Id id) {
        return mariaDBPropertyRepository.delete(id);
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