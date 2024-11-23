package com.example.saebackend.data.mariadb.properties.location;

import com.example.saebackend.base.Mapper;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaPropertyLocationRepository;
import com.example.saebackend.domain.properties.PropertyLocation;

import java.util.UUID;

public class LocationMapper implements Mapper<LocationEntity, PropertyLocation> {
    private final JpaPropertyLocationRepository jpaPropertyLocationRepository;

    public LocationMapper(JpaPropertyLocationRepository jpaPropertyLocationRepository) {
        this.jpaPropertyLocationRepository = jpaPropertyLocationRepository;
    }

    @Override
    public PropertyLocation mapTo(LocationEntity input) {
        return new PropertyLocation(input.getLatitude(), input.getLongitude(), input.getAddress(), input.getCity(), input.getPostalCode(), input.getCountry());
    }

    @Override
    public LocationEntity mapFrom(PropertyLocation output) {
        LocationEntity entity = jpaPropertyLocationRepository.findByAddress(output.getAddress());
        if(entity == null) entity = new LocationEntity(UUID.randomUUID().toString(), output.getLatitude(), output.getLongitude(), output.getAddress(), output.getCity(), output.getPostalCode(), output.getCountry());
        return jpaPropertyLocationRepository.saveAndFlush(entity);
    }
}
