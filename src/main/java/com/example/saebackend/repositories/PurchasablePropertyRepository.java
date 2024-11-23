package com.example.saebackend.repositories;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.purchasable.PurchasableProperty;
import com.example.saebackend.domain.properties.purchasable.models.PurchasablePropertyInputModel;

import java.util.List;

public interface PurchasablePropertyRepository { // TODO(y a t-il besoin de deux repositories ? à tester)
    PurchasableProperty create(PurchasableProperty purchasableProperty);

    PurchasableProperty getById(Id id);

    List<PurchasableProperty> getAll();

    PurchasableProperty update(Id id, PurchasablePropertyInputModel propertyModel);

    boolean deleteById(Id id);

   List<PurchasableProperty> getByOwnerId(Id ownerId);
}
