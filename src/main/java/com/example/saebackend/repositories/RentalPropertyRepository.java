package com.example.saebackend.repositories;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.rental.RentalProperty;

import java.util.List;

public interface RentalPropertyRepository { // TODO(y a t-il besoin de deux repositories ? à tester)
    RentalProperty create(RentalProperty rentalProperty);

    RentalProperty getById(Id id);

    List<RentalProperty> getAll();

    RentalProperty update(Id id, RentalProperty rentalProperty);

    boolean deleteById(Id id);
}
