package com.example.saebackend.domain.properties.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClimateClassTest {

    @Test
    void fromString_withValidString_returnsCorrectClimateClass() {
        assertEquals(ClimateClass.A, ClimateClass.fromString("A"));
        assertEquals(ClimateClass.B, ClimateClass.fromString("B"));
        assertEquals(ClimateClass.C, ClimateClass.fromString("C"));
        assertEquals(ClimateClass.D, ClimateClass.fromString("D"));
        assertEquals(ClimateClass.E, ClimateClass.fromString("E"));
        assertEquals(ClimateClass.F, ClimateClass.fromString("F"));
        assertEquals(ClimateClass.G, ClimateClass.fromString("G"));
    }

    @Test
    void fromString_withInvalidString_returnsNull() {
        assertNull(ClimateClass.fromString("H"));
        assertNull(ClimateClass.fromString(""));
        assertNull(ClimateClass.fromString(null));
    }

    @Test
    void toString_withValidClimateClass_returnsCorrectString() {
        assertEquals("A", ClimateClass.toString(ClimateClass.A));
        assertEquals("B", ClimateClass.toString(ClimateClass.B));
        assertEquals("C", ClimateClass.toString(ClimateClass.C));
        assertEquals("D", ClimateClass.toString(ClimateClass.D));
        assertEquals("E", ClimateClass.toString(ClimateClass.E));
        assertEquals("F", ClimateClass.toString(ClimateClass.F));
        assertEquals("G", ClimateClass.toString(ClimateClass.G));
    }
}