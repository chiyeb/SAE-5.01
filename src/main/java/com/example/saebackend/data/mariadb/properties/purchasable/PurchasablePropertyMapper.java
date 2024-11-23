package com.example.saebackend.data.mariadb.properties.purchasable;

import com.example.saebackend.base.Mapper;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaPropertyLocationRepository;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaPurchasablePropertyRepository;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaRoomTypeRepository;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaUserRepository;
import com.example.saebackend.data.mariadb.properties.location.LocationMapper;
import com.example.saebackend.data.mariadb.properties.roomtype.RoomTypeMapper;
import com.example.saebackend.data.mariadb.users.UserModelMapper;
import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.PropertyRoomData;
import com.example.saebackend.domain.properties.enums.ClimateClass;
import com.example.saebackend.domain.properties.enums.EnergyClass;
import com.example.saebackend.domain.properties.enums.PropertyType;
import com.example.saebackend.domain.properties.purchasable.PurchasableProperty;

import java.util.Map;
import java.util.stream.Collectors;

public class PurchasablePropertyMapper implements Mapper<PurchasablePropertyEntity, PurchasableProperty>{
    private final JpaPurchasablePropertyRepository jpaPurchasablePropertyRepository;
    private final LocationMapper locationMapper;
    private final RoomTypeMapper roomTypeMapper;
    private final UserModelMapper userModelMapper;

    public PurchasablePropertyMapper(JpaPurchasablePropertyRepository jpaPurchasablePropertyRepository, JpaPropertyLocationRepository jpaPropertyLocationRepository, JpaRoomTypeRepository jpaRoomTypeRepository, JpaUserRepository jpaUserRepository) {
        this.jpaPurchasablePropertyRepository = jpaPurchasablePropertyRepository;
        this.locationMapper = new LocationMapper(jpaPropertyLocationRepository);
        this.roomTypeMapper = new RoomTypeMapper(jpaRoomTypeRepository);
        this.userModelMapper = new UserModelMapper(jpaUserRepository);
    }

    @Override
    public PurchasableProperty mapTo(PurchasablePropertyEntity input) {
        return new PurchasableProperty(
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
                input.getPrice(),
                userModelMapper.mapTo(input.getOwner())
        );
    }

    @Override
    public PurchasablePropertyEntity mapFrom(PurchasableProperty output) {
        return jpaPurchasablePropertyRepository.saveAndFlush(new PurchasablePropertyEntity(
                output.getId().toString(),
                userModelMapper.mapFrom(output.getOwner()),
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
                output.getPriceOrSubscriptionPrice()
        ));
    }
}
