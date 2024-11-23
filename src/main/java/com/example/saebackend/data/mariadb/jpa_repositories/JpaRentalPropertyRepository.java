package com.example.saebackend.data.mariadb.jpa_repositories;

import com.example.saebackend.data.mariadb.properties.rental.RentalPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRentalPropertyRepository extends JpaRepository<RentalPropertyEntity, String> {}
