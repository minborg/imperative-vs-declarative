package com.speedment.evaluation.imperative;

import com.speedment.evaluation.SumArray;

import java.util.Arrays;

public class SumArrayRecursive implements SumArray {

    @Override
    public long sum(int[] arr) {
        return sum(0, arr);
    }

    private static long sum(long head, int[] tail) {
        return tail.length == 0
        ? head
        : sum(head + tail[0], Arrays.copyOfRange(tail, 1, tail.length));
    }

}
