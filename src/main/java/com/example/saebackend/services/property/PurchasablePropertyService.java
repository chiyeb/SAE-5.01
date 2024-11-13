package com.example.saebackend.services.property;

import com.example.saebackend.domain.exceptions.NotFoundException;
import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.models.PropertyReadModel;
import com.example.saebackend.domain.properties.purchasable.PurchasableProperty;
import com.example.saebackend.domain.properties.purchasable.models.PurchasablePropertyInputModel;
import com.example.saebackend.repositories.PurchasablePropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasablePropertyService { // TODO(Les deux classes propertyservice se répètent, il faut trouver un moyen de les combiner yeah!)
    PurchasablePropertyRepository purchasablePropertyRepository;

    public PurchasablePropertyService(PurchasablePropertyRepository purchasablePropertyRepository) {
        this.purchasablePropertyRepository = purchasablePropertyRepository;
    }

    public PropertyReadModel create(PurchasablePropertyInputModel propertyModel) {
        return purchasablePropertyRepository.create(PurchasableProperty.createFromModel(propertyModel)).readModel();
    }

    public PropertyReadModel getById(String id) {
        PurchasableProperty purchasableProperty = purchasablePropertyRepository.getById(Id.fromString(id));
        if (purchasableProperty == null) throw NotFoundException.propertyNotFound(id);
        return purchasableProperty.readModel();
    }

    public List<PropertyReadModel> getAll() {
        return purchasablePropertyRepository.getAll().stream().map(PurchasableProperty::readModel).toList();
    }

    public PropertyReadModel update(String id, PurchasablePropertyInputModel propertyModel) {
        PurchasableProperty purchasableProperty = purchasablePropertyRepository.update(Id.fromString(id), PurchasableProperty.createFromModel(propertyModel));
        if (purchasableProperty == null) throw NotFoundException.propertyNotFound(id);
        return purchasableProperty.readModel();
    }

    public void deleteById(String id) {
        if (!purchasablePropertyRepository.deleteById(Id.fromString(id))) throw NotFoundException.propertyNotFound(id);
    }
}