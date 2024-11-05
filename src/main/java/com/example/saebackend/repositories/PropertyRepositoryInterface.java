package com.example.saebackend.repositories;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
public interface PropertyRepositoryInterface {

    public Property create(Property property);
    public Property update(Property property);
    public Property delete(Id id);
    public Property getById(Id id);
}
