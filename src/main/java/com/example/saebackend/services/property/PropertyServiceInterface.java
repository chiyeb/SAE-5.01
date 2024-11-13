package com.example.saebackend.services.property;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.Property;

import java.util.ArrayList;

public interface PropertyServiceInterface {

    Object create(Property property);
    Object update(Property property);
    Object delete(Id id);
    Property getById(Id id);
    ArrayList<Property> getAll();
}
