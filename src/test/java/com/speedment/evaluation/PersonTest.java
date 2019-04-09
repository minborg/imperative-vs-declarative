package com.speedment.evaluation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class PersonTest {

    private static final String FIRST_NAME = "Arne";
    private static final String LAST_NAME = "Svensson";
    private static final int BORN_YEAR = 1966;
    private static final String COUNTRY = "Sweden";
    private static final double SALARY = 1000000;

    private final Person instance = createPerson();

    @Test
    void firstName() {
        assertEquals(FIRST_NAME, instance.firstName());
    }

    @Test
    void lastName() {
        assertEquals(LAST_NAME, instance.lastName());
    }

    @Test
    void bornYear() {
        assertEquals(BORN_YEAR, instance.birthYear());
    }

    @Test
    void country() {
        assertEquals(COUNTRY, instance.country());
    }

    @Test
    void salary() {
        assertEquals(SALARY, instance.salary());
    }

    @Test
    void equals1() {
        assertTrue(instance.equals(instance));
        final Person expected = createPerson();
        assertEquals(expected, instance);

        assertFalse(instance.equals(1));
        final Person different = new Person(FIRST_NAME, LAST_NAME, BORN_YEAR, COUNTRY, SALARY - 1);
        assertFalse(instance.equals(different));

    }

    @Test
    void hashCode1() {
        final Person other = createPerson();
        assertEquals(other.hashCode(), instance.hashCode());
    }

    @Test
    void toString1() {
        final String s = instance.toString();
        assertTrue(s.contains(COUNTRY));
    }

    private static Person createPerson() {
        return new Person(FIRST_NAME, LAST_NAME, BORN_YEAR, COUNTRY, SALARY);
    }

}