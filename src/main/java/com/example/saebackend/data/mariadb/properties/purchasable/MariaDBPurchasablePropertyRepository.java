package com.example.saebackend.data.mariadb.properties.purchasable;

import com.example.saebackend.data.mariadb.jpa_repositories.JpaPurchasablePropertyRepository;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaRoomTypeRepository;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaPropertyLocationRepository;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaUserRepository;
import com.example.saebackend.domain.exceptions.NotFoundException;
import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.purchasable.PurchasableProperty;
import com.example.saebackend.domain.properties.purchasable.models.PurchasablePropertyInputModel;
import com.example.saebackend.repositories.properties.PurchasablePropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the {@link PurchasablePropertyRepository} interface using MariaDB.
 * <p>
 * This repository manages CRUD operations for purchasable property-related data using
 * a JPA repository and maps between database entities and domain models.
 * </p>
 */
@Primary
@Repository
public class MariaDBPurchasablePropertyRepository implements PurchasablePropertyRepository {
    private final JpaPurchasablePropertyRepository jpaPurchasablePropertyRepository;
    private final PurchasablePropertyMapper mapper;

    @Autowired
    public MariaDBPurchasablePropertyRepository(JpaPurchasablePropertyRepository jpaPurchasablePropertyRepository, JpaRoomTypeRepository jpaRoomTypeRepository, JpaPropertyLocationRepository jpaPropertyLocationRepository, JpaUserRepository jpaUserRepository) {
        this.jpaPurchasablePropertyRepository = jpaPurchasablePropertyRepository;
        this.mapper = new PurchasablePropertyMapper(jpaPurchasablePropertyRepository,jpaPropertyLocationRepository, jpaRoomTypeRepository, jpaUserRepository);
    }

    @Override
    public PurchasableProperty create(PurchasableProperty purchasableProperty) {
        PurchasablePropertyEntity entity = mapper.mapFrom(purchasableProperty);
        return mapper.mapTo(entity);
    }

    @Override
    public PurchasableProperty getById(Id id) {
        try {
            return mapper.mapTo(jpaPurchasablePropertyRepository.getReferenceById(id.toString()));
        }
        catch(EntityNotFoundException e) {
            throw NotFoundException.propertyNotFound(id.toString());
        }
    }

    @Override
    public List<PurchasableProperty> getAll() {
        List<PurchasablePropertyEntity> purchasablePropertyEntities = jpaPurchasablePropertyRepository.findAll();
        return purchasablePropertyEntities.stream().map(mapper::mapTo).toList();
    }

    @Override
    public PurchasableProperty update(Id id, PurchasablePropertyInputModel propertyModel) {
        PurchasableProperty propertyToUpdate = getById(id);
        propertyToUpdate.updateFromModel(propertyModel);
        PurchasablePropertyEntity entity = mapper.mapFrom(propertyToUpdate);
        return mapper.mapTo(entity);
    }

    @Override
    public boolean deleteById(Id id) {
        jpaPurchasablePropertyRepository.deleteById(id.toString());
        return true;
    }

    @Override
    public List<PurchasableProperty> getByOwnerId(Id ownerId) {
        try {
            List<PurchasablePropertyEntity> purchasablePropertyEntities = jpaPurchasablePropertyRepository.findByOwner_Id(ownerId.toString());
            return purchasablePropertyEntities.stream().map(mapper::mapTo).toList();
        }
        catch(EntityNotFoundException e) {
            throw NotFoundException.propertyNotFound(ownerId.toString());
        }
    }
}
