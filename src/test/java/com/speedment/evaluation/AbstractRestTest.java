package com.speedment.evaluation;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public abstract class AbstractRestTest {

    private final Rest instance;

    public AbstractRestTest(Supplier<? extends Rest> constructor) {
        this.instance = constructor.get();
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "0, 1",
        "0, 1000",
        "1, 0",
        "1, 1",
        "49, 0",
        "49, 1",
        "50, 0",
        "50, 1",
        "51, 0",
        "51, 1",
        "200, 1",
        "200, 0",
        "10000, 0",
        "10000, 1",
        "10000, 2",
        "10000, 100",
        "10000, 100000000"
    })
    void test(int size, int page) {

        final Predicate<Person> predicate = p -> p.birthYear() > 1970;
        final Comparator<Person> order = Comparator.comparingInt(Person::birthYear);
        final List<Person> persons = PersonUtil.persons(size);

        final List<Person> expected = persons.stream()
            .filter(predicate)
            .sorted(order)
            .skip(RestUtil.PAGE_SIZE * page)
            .limit(RestUtil.PAGE_SIZE)
            .collect(collectingAndThen(toList(), Collections::unmodifiableList));

        final List<Person> actual = instance.page(persons, predicate, order, page);

        assertEquals(expected, actual);
        //System.out.println("actual = " + actual);
    }


}
