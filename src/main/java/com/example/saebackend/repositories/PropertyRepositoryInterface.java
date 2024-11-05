package com.example.saebackend.repositories;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
public interface PropertyRepositoryInterface {

    public void create(Property property);
    public void update(Id id, Property property);
    public void delete(Id id);
    public Property getById(Id id);
}
