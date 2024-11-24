package com.example.saebackend.data.mariadb.jpa_repositories;

import com.example.saebackend.data.mariadb.properties.location.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface provides methods for performing CRUD operations and additional query methods for LocationEntities.
 */
public interface JpaPropertyLocationRepository extends JpaRepository<LocationEntity, String> {
    LocationEntity findByAddress(String address);
}
