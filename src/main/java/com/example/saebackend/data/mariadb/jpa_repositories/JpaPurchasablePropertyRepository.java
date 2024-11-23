package com.example.saebackend.data.mariadb.jpa_repositories;

import com.example.saebackend.data.mariadb.properties.purchasable.PurchasablePropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPurchasablePropertyRepository extends JpaRepository<PurchasablePropertyEntity, String> {
}
