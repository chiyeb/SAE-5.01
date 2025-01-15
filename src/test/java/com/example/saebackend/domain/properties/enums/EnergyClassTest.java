package com.example.saebackend.domain.properties.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnergyClassTest {

    @Test
    void fromString_withValidString_returnsCorrectEnergyClass() {
        assertEquals(EnergyClass.A, EnergyClass.fromString("A"));
        assertEquals(EnergyClass.B, EnergyClass.fromString("B"));
        assertEquals(EnergyClass.C, EnergyClass.fromString("C"));
        assertEquals(EnergyClass.D, EnergyClass.fromString("D"));
        assertEquals(EnergyClass.E, EnergyClass.fromString("E"));
        assertEquals(EnergyClass.F, EnergyClass.fromString("F"));
        assertEquals(EnergyClass.G, EnergyClass.fromString("G"));
    }

    @Test
    void fromString_withInvalidString_returnsNull() {
        assertNull(EnergyClass.fromString("H"));
        assertNull(EnergyClass.fromString(""));
        assertNull(EnergyClass.fromString(null));
    }

    @Test
    void fromString_withLowerCaseString_returnsNull() {
        assertNull(EnergyClass.fromString("a"));
        assertNull(EnergyClass.fromString("b"));
        assertNull(EnergyClass.fromString("c"));
    }

    @Test
    void toString_withValidEnergyClass_returnsCorrectString() {
        assertEquals("A", EnergyClass.toString(EnergyClass.A));
        assertEquals("B", EnergyClass.toString(EnergyClass.B));
        assertEquals("C", EnergyClass.toString(EnergyClass.C));
        assertEquals("D", EnergyClass.toString(EnergyClass.D));
        assertEquals("E", EnergyClass.toString(EnergyClass.E));
        assertEquals("F", EnergyClass.toString(EnergyClass.F));
        assertEquals("G", EnergyClass.toString(EnergyClass.G));
    }
}