package com.example.saebackend.data.mariadb.properties.purchasable;

import com.example.saebackend.data.mariadb.properties.jpa_repositories.*;
import com.example.saebackend.data.mariadb.properties.jpa_repositories.JpaPropertyLocationRepository;
import com.example.saebackend.domain.exceptions.NotFoundException;
import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.purchasable.PurchasableProperty;
import com.example.saebackend.domain.properties.purchasable.models.PurchasablePropertyInputModel;
import com.example.saebackend.repositories.PurchasablePropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class MariaDBPurchasablePropertyRepository implements PurchasablePropertyRepository {
    private final JpaPurchasablePropertyRepository jpaPurchasablePropertyRepository;
    private final PurchasablePropertyMapper purchasablePropertyMapper;

    @Autowired
    public MariaDBPurchasablePropertyRepository(JpaPurchasablePropertyRepository jpaPurchasablePropertyRepository, JpaRoomTypeRepository jpaRoomTypeRepository, JpaPropertyLocationRepository jpaPropertyLocationRepository) {
        this.jpaPurchasablePropertyRepository = jpaPurchasablePropertyRepository;
        this.purchasablePropertyMapper = new PurchasablePropertyMapper(jpaPurchasablePropertyRepository,jpaPropertyLocationRepository, jpaRoomTypeRepository);
    }

    @Override
    public PurchasableProperty create(PurchasableProperty purchasableProperty) {
        PurchasablePropertyEntity entity = purchasablePropertyMapper.mapFrom(purchasableProperty);
        return purchasablePropertyMapper.mapTo(entity);
    }

    @Override
    public PurchasableProperty getById(Id id) {
        try {
            return purchasablePropertyMapper.mapTo(jpaPurchasablePropertyRepository.getReferenceById(id.toString()));
        }
        catch(EntityNotFoundException e) {
            throw NotFoundException.propertyNotFound(id.toString());
        }
    }

    @Override
    public List<PurchasableProperty> getAll() {
        List<PurchasablePropertyEntity> purchasablePropertyEntities = jpaPurchasablePropertyRepository.findAll();
        return purchasablePropertyEntities.stream().map(purchasablePropertyMapper::mapTo).toList();
    }

    @Override
    public PurchasableProperty update(Id id, PurchasablePropertyInputModel propertyModel) {
        PurchasableProperty propertyToUpdate = getById(id);
        propertyToUpdate.updateFromModel(propertyModel);
        PurchasablePropertyEntity entity = purchasablePropertyMapper.mapFrom(propertyToUpdate);
        return purchasablePropertyMapper.mapTo(entity);
    }

    @Override
    public boolean deleteById(Id id) {
        jpaPurchasablePropertyRepository.deleteById(id.toString());
        return true;
    }
}
