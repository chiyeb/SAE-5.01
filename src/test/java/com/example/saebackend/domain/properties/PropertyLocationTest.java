package com.example.saebackend.domain.properties;

import com.example.saebackend.domain.properties.models.PropertyLocationModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertyLocationTest {

    @Test
    void createFromModel_withValidModel_createsPropertyLocation() {
        PropertyLocationModel model = new PropertyLocationModel(40.7128, -74.0060, "123 Main St", "New York", "10001", "USA");
        PropertyLocation location = PropertyLocation.createFromModel(model);

        assertEquals(40.7128, location.getLatitude());
        assertEquals(-74.0060, location.getLongitude());
        assertEquals("123 Main St", location.getAddress());
        assertEquals("New York", location.getCity());
        assertEquals("10001", location.getPostalCode());
        assertEquals("USA", location.getCountry());
    }

    @Test
    void readModel_returnsCorrectModel() {
        PropertyLocation location = new PropertyLocation(40.7128, -74.0060, "123 Main St", "New York", "10001", "USA");
        PropertyLocationModel model = location.readModel();

        assertEquals(40.7128, model.latitude());
        assertEquals(-74.0060, model.longitude());
        assertEquals("123 Main St", model.address());
        assertEquals("New York", model.city());
        assertEquals("10001", model.postalCode());
        assertEquals("USA", model.country());
    }
}