package com.example.saebackend.services.property;

import com.example.saebackend.domain.exceptions.NotFoundException;
import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.models.PropertyReadModel;
import com.example.saebackend.domain.properties.purchasable.PurchasableProperty;
import com.example.saebackend.domain.properties.purchasable.models.PurchasablePropertyInputModel;
import com.example.saebackend.repositories.properties.PurchasablePropertyRepository;
import com.example.saebackend.repositories.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing purchasable properties.
 * Provides methods for creating, retrieving, updating, and deleting purchasable properties.
 */
@Service
public class PurchasablePropertyService { // TODO(Les deux classes propertyservice se répètent, il faut trouver un moyen de les combiner yeah!)
    PurchasablePropertyRepository purchasablePropertyRepository;
    UserRepository userRepository;

    public PurchasablePropertyService(PurchasablePropertyRepository purchasablePropertyRepository, UserRepository userRepository) {
        this.purchasablePropertyRepository = purchasablePropertyRepository;
        this.userRepository = userRepository;
    }

    public PropertyReadModel create(PurchasablePropertyInputModel propertyModel, String userId) {
        return purchasablePropertyRepository.create(PurchasableProperty.createFromModel(propertyModel,userRepository.getById(Id.fromString(userId)))).readModel();
    }

    public PropertyReadModel getById(String id) {
        PurchasableProperty purchasableProperty = purchasablePropertyRepository.getById(Id.fromString(id));
        if (purchasableProperty == null) throw NotFoundException.propertyNotFound(id);
        return purchasableProperty.readModel();
    }

    public List<PropertyReadModel> getAll() {
        return purchasablePropertyRepository.getAll().stream().map(PurchasableProperty::readModel).toList();
    }

    public PropertyReadModel update(String id, PurchasablePropertyInputModel propertyModel) { //
        PurchasableProperty purchasableProperty = purchasablePropertyRepository.update(Id.fromString(id), propertyModel);
        if (purchasableProperty == null) throw NotFoundException.propertyNotFound(id);
        return purchasableProperty.readModel();
    }

    public void deleteById(String id) {
        if (!purchasablePropertyRepository.deleteById(Id.fromString(id))) throw NotFoundException.propertyNotFound(id);
    }

    public List<PropertyReadModel> getByOwnerId(String ownerId) {
        return purchasablePropertyRepository.getByOwnerId(Id.fromString(ownerId)).stream().map(PurchasableProperty::readModel).toList();
    }
}