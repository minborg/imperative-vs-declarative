package com.speedment.evaluation.imperative;

import com.speedment.evaluation.SumArray;

public class SumArrayImperative implements SumArray {

    @Override
    public long sum(int[] arr) {
        long sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

}
