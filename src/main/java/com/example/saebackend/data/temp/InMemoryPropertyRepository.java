package com.example.saebackend.data.temp;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.Property;
import com.example.saebackend.repositories.temp.PropertyRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * In-memory implementation of the {@link PropertyRepository} interface.
 * <p>
 * This class is primarily used for testing or temporary storage purposes.
 * It uses a {@link HashMap} to store property data in memory, simulating a database.
 * Note that all data will be lost when the application stops running.
 * </p>
 */
@Repository
public class InMemoryPropertyRepository implements PropertyRepository {

    private final HashMap<Id, Property> properties = new HashMap<>();

    /**
     * Creates a new property and stores it in memory.
     *
     * @param property the property to be created.
     * @return the created property.
     */
    @Override
    public Property create(Property property) {
        properties.put(property.getId(), property);
        return property;
    }

    /**
     * Updates an existing property in memory.
     * <p>
     * If the property does not already exist, it will be added as a new entry.
     * </p>
     *
     * @param property the property to be updated.
     * @return the updated property.
     */
    @Override
    public Property update(Property property) {
        properties.put(property.getId(), property);
        return property;
    }

    /**
     * Deletes a property from memory by its ID.
     *
     * @param id the ID of the property to delete.
     * @return the deleted property, or {@code null} if no property was found with the specified ID.
     */
    @Override
    public Property delete(Id id) {
        return properties.remove(id);
    }

    /**
     * Retrieves a property from memory by its ID.
     *
     * @param id the ID of the property to retrieve.
     * @return the property with the specified ID, or {@code null} if no property is found.
     */
    @Override
    public Property getById(Id id) {
        return properties.get(id);
    }
}
