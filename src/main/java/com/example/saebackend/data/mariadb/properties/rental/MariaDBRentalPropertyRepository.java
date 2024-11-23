package com.example.saebackend.data.mariadb.properties.rental;

import com.example.saebackend.data.mariadb.jpa_repositories.JpaPropertyLocationRepository;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaRentalPropertyRepository;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaRoomTypeRepository;
import com.example.saebackend.domain.exceptions.NotFoundException;
import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.rental.RentalProperty;
import com.example.saebackend.domain.properties.rental.models.RentalPropertyInputModel;
import com.example.saebackend.repositories.RentalPropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class MariaDBRentalPropertyRepository implements RentalPropertyRepository {
    private final JpaRentalPropertyRepository jpaRentalPropertyRepository;
    private final RentalPropertyMapper mapper;

    @Autowired
    public MariaDBRentalPropertyRepository(JpaRentalPropertyRepository jpaRentalPropertyRepository, JpaRoomTypeRepository jpaRoomTypeRepository, JpaPropertyLocationRepository jpaPropertyLocationRepository) {
        this.jpaRentalPropertyRepository = jpaRentalPropertyRepository;
        this.mapper = new RentalPropertyMapper(jpaRentalPropertyRepository, jpaPropertyLocationRepository, jpaRoomTypeRepository);
    }

    @Override
    public RentalProperty create(RentalProperty rentalProperty) {
        RentalPropertyEntity entity = mapper.mapFrom(rentalProperty);
        return mapper.mapTo(entity);
    }

    @Override
    public RentalProperty getById(Id id) {
        try {
            return mapper.mapTo(jpaRentalPropertyRepository.getReferenceById(id.toString()));
        }
        catch(EntityNotFoundException e) {
                throw NotFoundException.propertyNotFound(id.toString());
        }
    }

    @Override
    public List<RentalProperty> getAll() {
        List<RentalPropertyEntity> rentalPropertyEntities = jpaRentalPropertyRepository.findAll();
        return rentalPropertyEntities.stream().map(mapper::mapTo).toList();
    }

    @Override
    public RentalProperty update(Id id, RentalPropertyInputModel propertyModel) {
        RentalProperty propertyToUpdate = getById(id);
        propertyToUpdate.updateFromModel(propertyModel);
        RentalPropertyEntity entity = mapper.mapFrom(propertyToUpdate);
        return mapper.mapTo(entity);
    }

    @Override
    public boolean deleteById(Id id) {
        jpaRentalPropertyRepository.deleteById(id.toString());
        return true;
    }
}
