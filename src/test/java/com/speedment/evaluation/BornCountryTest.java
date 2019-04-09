package com.speedment.evaluation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class BornCountryTest {

    private static final int BORN_YEAR = 1966;
    private static final String COUNTRY = "Sweden";

    private final YearCountry instance = create();


    @Test
    void bornYear() {
        assertEquals(BORN_YEAR, instance.birthYear());
    }

    @Test
    void country() {
        assertEquals(COUNTRY, instance.country());
    }

    @Test
    void equals1() {
        final YearCountry expected = create();
        assertEquals(expected, instance);
    }

    @Test
    void hashCode1() {
        final YearCountry other = create();
        assertEquals(other.hashCode(), instance.hashCode());
        assertFalse(instance.equals(1));

        final YearCountry different = new YearCountry(BORN_YEAR, "Swaziland");
        assertFalse(instance.equals(different));
    }

    @Test
    void toString1() {
        final String s = instance.toString();
        assertTrue(s.contains(COUNTRY));
    }

    private static YearCountry create() {
        return new YearCountry(BORN_YEAR, COUNTRY);
    }

}