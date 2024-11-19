package com.example.saebackend.data.mariadb.properties.jpa_repositories;

import com.example.saebackend.data.mariadb.properties.roomtype.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoomTypeRepository extends JpaRepository<RoomTypeEntity, String> {}
