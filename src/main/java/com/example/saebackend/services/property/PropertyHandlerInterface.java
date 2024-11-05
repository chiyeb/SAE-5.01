package com.example.saebackend.services.property;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public interface PropertyHandlerInterface {
    Object create(Property property);
    Object update(Property property);
    Object delete(Id id);
    Property getById(Id id);
    Object getAll();

}