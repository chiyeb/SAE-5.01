package com.example.saebackend.domain.properties.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PropertyTypeTest {

    @Test
    void fromString_withValidString_returnsCorrectPropertyType() {
        assertEquals(PropertyType.HOUSE, PropertyType.fromString("HOUSE"));
        assertEquals(PropertyType.APARTMENT, PropertyType.fromString("APARTMENT"));
    }

    @Test
    void fromString_withInvalidString_returnsNull() {
        assertNull(PropertyType.fromString("VILLA"));
        assertNull(PropertyType.fromString(""));
    }

    @Test
    void toString_withValidPropertyType_returnsCorrectString() {
        assertEquals("HOUSE", PropertyType.toString(PropertyType.HOUSE));
        assertEquals("APARTMENT", PropertyType.toString(PropertyType.APARTMENT));
    }


    @Test
    void isValid_withValidString_returnsTrue() {
        assertTrue(PropertyType.isValid("HOUSE"));
        assertTrue(PropertyType.isValid("APARTMENT"));
    }

    @Test
    void isValid_withInvalidString_returnsFalse() {
        assertFalse(PropertyType.isValid("VILLA"));
        assertFalse(PropertyType.isValid(""));
    }
}