package com.example.saebackend.repositories;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.purchasable.PurchasableProperty;

import java.util.List;

public interface PurchasablePropertyRepository { // TODO(y a t-il besoin de deux repositories ? à tester)
    PurchasableProperty create(PurchasableProperty purchasableProperty);

    PurchasableProperty getById(Id id);

    List<PurchasableProperty> getAll();

    PurchasableProperty update(Id id, PurchasableProperty purchasableProperty);

    boolean deleteById(Id id);
}
