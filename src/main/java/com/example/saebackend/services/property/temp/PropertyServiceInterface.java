package com.example.saebackend.services.property.temp;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.Property;
import com.example.saebackend.domain.properties.models.PropertyReadModel;

import java.util.List;

public interface PropertyServiceInterface {

    PropertyReadModel create(Property property);
    PropertyReadModel update(Property property);
    void delete(Id id);
    PropertyReadModel getById(Id id);
    List<PropertyReadModel> getAll();
}
