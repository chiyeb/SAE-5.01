package com.example.saebackend.data.mariadb.jpa_repositories;

import com.example.saebackend.data.mariadb.properties.purchasable.PurchasablePropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface provides methods for performing CRUD operations and additional query methods for PurchasablePropertyEntities.
 */
@Repository
public interface JpaPurchasablePropertyRepository extends JpaRepository<PurchasablePropertyEntity, String> {
    List<PurchasablePropertyEntity> findByOwner_Id(String ownerId);
}
