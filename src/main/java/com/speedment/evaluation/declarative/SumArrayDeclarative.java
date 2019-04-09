package com.speedment.evaluation.declarative;

import com.speedment.evaluation.SumArray;

import java.util.stream.IntStream;

public class SumArrayDeclarative implements SumArray {

    @Override
    public long sum(int[] arr) {
        return IntStream.of(arr)
            .mapToLong(i -> i)
            .sum();
    }
}
