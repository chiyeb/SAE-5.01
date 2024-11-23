package com.example.saebackend.data.mariadb.properties;

import com.example.saebackend.data.mariadb.properties.location.LocationEntity;
import com.example.saebackend.data.mariadb.properties.roomtype.RoomTypeEntity;
import com.example.saebackend.data.mariadb.users.UserModelEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "property")
@Inheritance(strategy = InheritanceType.JOINED)
public class PropertyEntity {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserModelEntity owner;
    private String type;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @ElementCollection
    @CollectionTable(
            name = "images",
            joinColumns = @JoinColumn(name = "property_id")
    )
    @Column(name = "url")
    private List<String> images;

    @Column(name = "living_area")
    private double livingArea;
    @Column(name = "land_area")
    private double landArea;

    @ElementCollection
    @CollectionTable(
            name = "property_room_count",
            joinColumns = @JoinColumn(name = "property_id")
    )
    @SuppressWarnings("JpaDataSourceORMInspection")
    @MapKeyJoinColumn(name="room_name")
    @Column(name = "count")
    private Map<RoomTypeEntity, Integer> rooms;

    private String orientation;
    @Column(name = "energy_class")
    private String energyClass;
    @Column(name = "climate_class")
    private String climateClass;
    private String view;
    @Column(name = "estimation_energy_cost")
    private double estimationEnergyCost;

    public PropertyEntity() {}

    public PropertyEntity(String id, UserModelEntity owner, String type, String title, String description, LocationEntity location, List<String> images, double livingArea, double landArea, Map<RoomTypeEntity, Integer> rooms, String orientation, String energyClass, String climateClass, String view, double estimationEnergyCost) {
        this.id = id;
        this.owner = owner;
        this.type = type;
        this.title = title;
        this.description = description;
        this.location = location;
        this.images = images;
        this.livingArea = livingArea;
        this.landArea = landArea;
        this.rooms = rooms;
        this.orientation = orientation;
        this.energyClass = energyClass;
        this.climateClass = climateClass;
        this.view = view;
        this.estimationEnergyCost = estimationEnergyCost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserModelEntity getOwner() {
        return owner;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public double getLivingArea() {
        return livingArea;
    }

    public double getLandArea() {
        return landArea;
    }

    public Map<RoomTypeEntity, Integer> getRooms() {
        return rooms;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getEnergyClass() {
        return energyClass;
    }

    public String getClimateClass() {
        return climateClass;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public double getEstimationEnergyCost() {
        return estimationEnergyCost;
    }
}
