package com.example.saebackend.data.mariadb.properties.rental;

import com.example.saebackend.base.Mapper;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaPropertyLocationRepository;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaRentalPropertyRepository;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaRoomTypeRepository;
import com.example.saebackend.data.mariadb.properties.location.LocationMapper;
import com.example.saebackend.data.mariadb.properties.roomtype.RoomTypeMapper;
import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.PropertyRoomData;
import com.example.saebackend.domain.properties.enums.ClimateClass;
import com.example.saebackend.domain.properties.enums.EnergyClass;
import com.example.saebackend.domain.properties.enums.PropertyType;
import com.example.saebackend.domain.properties.rental.RentalProperty;
import com.example.saebackend.domain.properties.rental.SubscriptionFrequency;

import java.util.Map;
import java.util.stream.Collectors;

public class RentalPropertyMapper implements Mapper<RentalPropertyEntity, RentalProperty>{
    private final JpaRentalPropertyRepository jpaRentalPropertyRepository;
    private final LocationMapper locationMapper;
    private final RoomTypeMapper roomTypeMapper;

    public RentalPropertyMapper(JpaRentalPropertyRepository jpaRentalPropertyRepository, JpaPropertyLocationRepository jpaPropertyLocationRepository, JpaRoomTypeRepository jpaRoomTypeRepository) {
        this.jpaRentalPropertyRepository = jpaRentalPropertyRepository;
        this.locationMapper = new LocationMapper(jpaPropertyLocationRepository);
        this.roomTypeMapper = new RoomTypeMapper(jpaRoomTypeRepository);
    }

    @Override
    public RentalProperty mapTo(RentalPropertyEntity input) {
        return new RentalProperty(
                Id.fromString(input.getId()),
                PropertyType.fromString(input.getType()),
                input.getTitle(),
                input.getDescription(),
                locationMapper.mapTo(input.getLocation()),
                input.getImages(),
                input.getLivingArea(),
                input.getLandArea(),
                new PropertyRoomData(input.getRooms().entrySet().stream().collect(Collectors.toMap(e -> roomTypeMapper.mapTo(e.getKey()), Map.Entry::getValue))),
                input.getOrientation(),
                EnergyClass.fromString(input.getEnergyClass()),
                ClimateClass.fromString(input.getClimateClass()),
                input.getView(),
                input.getEstimationEnergyCost(),
                SubscriptionFrequency.fromString(input.getRentFrequency()),
                input.getRent()
        );
    }

    @Override
    public RentalPropertyEntity mapFrom(RentalProperty output) {
        return jpaRentalPropertyRepository.saveAndFlush(new RentalPropertyEntity(
                output.getId().toString(),
                output.getType().toString(),
                output.getTitle(),
                output.getDescription(),
                locationMapper.mapFrom(output.getLocation()),
                output.getImages(),
                output.getLivingArea(),
                output.getLandArea(),
                output.getRooms().getRooms().entrySet().stream().collect(Collectors.toMap(e -> roomTypeMapper.mapFrom(e.getKey()), Map.Entry::getValue)),
                output.getOrientation(),
                output.getEnergyClass().toString(),
                output.getClimateClass().toString(),
                output.getView(),
                output.getEstimationCostEnergy(),
                output.getPriceOrSubscriptionPrice(),
                output.getSubscriptionFrequency().toString()
        ));
    }
}
