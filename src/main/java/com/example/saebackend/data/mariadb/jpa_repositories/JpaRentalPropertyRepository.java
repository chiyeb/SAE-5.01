package com.example.saebackend.data.mariadb.jpa_repositories;

import com.example.saebackend.data.mariadb.properties.rental.RentalPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaRentalPropertyRepository extends JpaRepository<RentalPropertyEntity, String> {
    List<RentalPropertyEntity> findByOwner_Id(String ownerId);
}
