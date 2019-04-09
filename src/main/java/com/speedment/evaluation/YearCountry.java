package com.speedment.evaluation;

import java.util.Objects;

public final class YearCountry {

    private final int birthYear;
    private final String country;

    public YearCountry(int birthYear, String country) {
        this.birthYear = birthYear;
        this.country = country;
    }

    public YearCountry(Person person) {
        this.birthYear = person.birthYear();
        this.country = person.country();
    }

    public int birthYear() { return birthYear; }

    public String country() { return country; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YearCountry that = (YearCountry) o;
        return birthYear == that.birthYear &&
            Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthYear, country);
    }

    @Override
    public String toString() {
        return "BornCountry{" +
            "birthYear=" + birthYear +
            ", country='" + country + '\'' +
            '}';
    }
}


