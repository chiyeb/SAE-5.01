package com.example.saebackend.services.property;

import com.example.saebackend.domain.exceptions.NotFoundException;
import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.models.PropertyReadModel;
import com.example.saebackend.domain.properties.rental.RentalProperty;
import com.example.saebackend.domain.properties.rental.models.RentalPropertyInputModel;
import com.example.saebackend.repositories.RentalPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalPropertyService { // TODO(Les deux classes propertyservice se répètent, il faut trouver un moyen de les combiner yeah!)
    RentalPropertyRepository rentalPropertyRepository;

    public RentalPropertyService(RentalPropertyRepository rentalPropertyRepository) {
        this.rentalPropertyRepository = rentalPropertyRepository;
    }

    public PropertyReadModel create(RentalPropertyInputModel propertyModel) {
        return rentalPropertyRepository.create(RentalProperty.createFromModel(propertyModel)).readModel();
    }

    public PropertyReadModel getById(String id) {
        RentalProperty rentalProperty = rentalPropertyRepository.getById(Id.fromString(id));
        if (rentalProperty == null) throw NotFoundException.propertyNotFound(id);
        return rentalProperty.readModel();
    }

    public List<PropertyReadModel> getAll() {
        return rentalPropertyRepository.getAll().stream().map(RentalProperty::readModel).toList();
    }

    public PropertyReadModel update(String id, RentalPropertyInputModel propertyModel) {
        RentalProperty rentalProperty = rentalPropertyRepository.update(Id.fromString(id), propertyModel);
        if (rentalProperty == null) throw NotFoundException.propertyNotFound(id);
        return rentalProperty.readModel();
    }

    public void deleteById(String id) {
        if (!rentalPropertyRepository.deleteById(Id.fromString(id))) throw NotFoundException.propertyNotFound(id);
    }
}
