package com.example.saebackend.data.mariadb.properties.roomtype;

import com.example.saebackend.base.Mapper;
import com.example.saebackend.data.mariadb.properties.jpa_repositories.JpaRoomTypeRepository;
import com.example.saebackend.domain.properties.RoomType;

public class RoomTypeMapper implements Mapper<RoomTypeEntity, RoomType> {
    private final JpaRoomTypeRepository jpaRoomTypeRepository;

    public RoomTypeMapper(JpaRoomTypeRepository jpaRoomTypeRepository) {
        this.jpaRoomTypeRepository = jpaRoomTypeRepository;
    }

    @Override
    public RoomType mapTo(RoomTypeEntity input) {
        return new RoomType(input.getName());
    }

    @Override
    public RoomTypeEntity mapFrom(RoomType output) {
        return jpaRoomTypeRepository.saveAndFlush(new RoomTypeEntity(output.name()));
    }
}
