package com.example.saebackend.data;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.Property;
import com.example.saebackend.repositories.PropertyRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MariaDBPropertyRepository implements PropertyRepository {

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
