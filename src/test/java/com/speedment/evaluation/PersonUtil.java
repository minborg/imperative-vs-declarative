package com.speedment.evaluation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public enum PersonUtil {;

    public static List<Person> persons(int size) {
        return IntStream.range(0, size)
            .mapToObj(i -> new Person("A", "B", 1950 + i % 70, country(i), salary(i)))
            .collect(toList());
    }

    private static final List<String> COUNTRIES = Stream.of("Andorra", "Belgium", "Congo", "Denmark", "Eire", "France", "Georgia", "Hungary", "Iceland")
        .collect(Collectors.toList());

    private static String country(int index) {
        return COUNTRIES.get(index % COUNTRIES.size());
    }

    private static double salary(int index) {
        double countryFactor = (country(index).hashCode() & 0x7FFFFFFF) / Integer.MAX_VALUE;
        return 30_000 + countryFactor * 50_000 + index * 147 % 10_000;
    }


}
