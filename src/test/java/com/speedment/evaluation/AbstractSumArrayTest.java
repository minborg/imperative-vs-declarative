package com.speedment.evaluation;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public abstract class AbstractSumArrayTest {

    private final SumArray instance;
    private final Random rnd;

    public AbstractSumArrayTest(Supplier<? extends SumArray> constructor) {
        this.instance = requireNonNull(constructor).get();
        rnd = new Random(42);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 100, 100_000})
    void test(int i) {
        final int[] arr = IntStream.range(0, i).map(j -> rnd.nextInt(256) - 128).toArray();
        final long expected = IntStream.of(arr).mapToLong(j -> j).sum();
        final long actual = instance.sum(arr);
        assertEquals(expected, actual);
    }

}
