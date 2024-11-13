package com.example.saebackend.services.property.temp;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.Property;

public interface PropertyHandlerInterface {
    Object create(Property property);
    Object update(Property property);
    Object delete(Id id);
    Property getById(Id id);
    Object getAll();

}