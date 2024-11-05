package com.example.saebackend.repositories;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
import org.springframework.stereotype.Repository;

@Repository
public class MariaDBPropertyRepository implements PropertyRepositoryInterface {

    @Override
    public Property create(Property property) {
        return property;
    }

    @Override
    public Property update(Property property) {
        //TODO: récuperer l'id de la propriété et la mettre à jour
        return property;
    }

    @Override
    public Property delete(Id id) {
        return null;
    }

    @Override
    public Property getById(Id id) {
        return null;
    }
}
