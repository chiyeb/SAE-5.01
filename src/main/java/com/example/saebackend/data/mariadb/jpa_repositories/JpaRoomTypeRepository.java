package com.example.saebackend.data.mariadb.jpa_repositories;

import com.example.saebackend.data.mariadb.properties.roomtype.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface provides methods for performing CRUD operations and additional query methods for RoomTypeEntities.
 */
@Repository
public interface JpaRoomTypeRepository extends JpaRepository<RoomTypeEntity, String> {}
