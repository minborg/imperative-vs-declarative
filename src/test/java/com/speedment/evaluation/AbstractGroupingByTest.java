package com.speedment.evaluation;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public abstract class AbstractGroupingByTest {

    private final GroupingBy instance;

    public AbstractGroupingByTest(Supplier<? extends GroupingBy> constructor) {
        this.instance = constructor.get();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 100, 100_000})
    void test(int size) {
        final List<Person> persons = PersonUtil.persons(size);

        final Map<Integer, Map<String, AtomicInteger>> counts = new HashMap<>();
        final Map<Integer, Map<String, MutableDouble>> sums = new HashMap<>();

        for (Person person : persons) {
            final int born = person.birthYear();
            final String country = person.country();
            counts.computeIfAbsent(born, $ -> new HashMap<>()).computeIfAbsent(country, $ -> new AtomicInteger()).incrementAndGet();
            sums.computeIfAbsent(born, $ -> new HashMap<>()).computeIfAbsent(country, $ -> new MutableDouble()).add(person.salary());
        }

        final Map<YearCountry, Double> expected = new HashMap<>();
        counts.forEach((k, v) -> {
            v.forEach((k2, v2) -> {
                double sum = sums.get(k).get(k2).get();
                YearCountry bc = new YearCountry(k, k2);
                expected.put(bc, sum / v2.get());
            });
        });

        final Map<YearCountry, Double> actual = instance.average(persons);

        assertEquals(expected, actual);
        //System.out.println("actual = " + actual);
    }


    static class MutableDouble {
        private double value;
        void add(double term) {
            value += term;
        }
        double get() {
            return value;
        }
    }

}
