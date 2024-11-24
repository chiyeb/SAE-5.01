package com.example.saebackend.repositories.properties;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.rental.RentalProperty;
import com.example.saebackend.domain.properties.rental.models.RentalPropertyInputModel;

import java.util.List;

/**
 * Repository interface for managing rental properties.
 * Provides methods for creating, retrieving, updating, and deleting rental properties.
 */
public interface RentalPropertyRepository { // TODO(y a t-il besoin de deux repositories ? à tester)
    RentalProperty create(RentalProperty rentalProperty);

    RentalProperty getById(Id id);

    List<RentalProperty> getAll();

    RentalProperty update(Id id, RentalPropertyInputModel propertyModel);

    boolean deleteById(Id id);

    List<RentalProperty> getByOwnerId(Id ownerId);
}
