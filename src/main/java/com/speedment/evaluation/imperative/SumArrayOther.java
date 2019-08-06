package com.speedment.evaluation.imperative;

import com.speedment.evaluation.SumArray;

import java.util.Arrays;

public class SumArrayOther implements SumArray {

    @Override
    public long sum(int[] arr) {
        return sum(0, arr, 0);
    }

    private static long sum(long head, int[] tail, int index) {
        while (index != tail.length) {
            head = head + tail[index++];
        }
        return head;
    }
}
