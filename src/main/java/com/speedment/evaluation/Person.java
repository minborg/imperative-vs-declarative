package com.speedment.evaluation;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class Person {

    private final String firstName;
    private final String lastName;
    private final int birthYear;
    private final String country;
    private final double salary;

    public Person(String firstName,
                  String lastName,
                  int birthYear,
                  String country,
                  double salary) {
        this.firstName = requireNonNull(firstName);
        this.lastName = requireNonNull(lastName);
        this.birthYear = birthYear;
        this.country = requireNonNull(country);
        this.salary = salary;
    }

    public String firstName() { return firstName; }

    public String lastName() { return lastName; }

    public int birthYear() { return birthYear; }

    public String country() { return country; }

    public double salary() { return salary; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return birthYear == person.birthYear &&
            Double.compare(person.salary, salary) == 0 &&
            Objects.equals(firstName, person.firstName) &&
            Objects.equals(lastName, person.lastName) &&
            Objects.equals(country, person.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthYear, country, salary);
    }

    @Override
    public String toString() {
        return "Person{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", bornYear=" + birthYear +
            ", country='" + country + '\'' +
            ", salary=" + salary +
            '}';
    }
}
