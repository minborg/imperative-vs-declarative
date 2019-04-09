package com.speedment.evaluation.imperative;

import com.speedment.evaluation.YearCountry;
import com.speedment.evaluation.GroupingBy;
import com.speedment.evaluation.Person;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class GroupingByImperativeFirstTry implements GroupingBy {

    @Override
    public Map<YearCountry, Double> average(Collection<Person> persons) {
        final List<Person> personList = new ArrayList<>(persons);
        final int threads = ForkJoinPool.commonPool().getParallelism();
        final int step = personList.size() / threads;

        // Divide the work into smaller work items
        final List<List<Person>> subLists = new ArrayList<>();
        for (int i = 0; i < threads - 1; i ++) {
            subLists.add(personList.subList(i * step, (i + 1) * step));
        }
        subLists.add(personList.subList((threads - 1) * step, personList.size()));

        // Submit the work items to the common ForkJoinPool
        final List<CompletableFuture<Map<YearCountry, double[]>>> futures = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            final List<Person> subList = subLists.get(i);
            futures.add(CompletableFuture.supplyAsync(() -> average(subList)));
        }

        // Wait for completion, get the result and combine the result
        final Map<YearCountry, double[]> merged = new HashMap<>();
        for (int i = 0; i < threads; i++) {
            merge(merged, futures.get(i).join());
        }

        // Construct the result
        final Map<YearCountry, Double> result = new HashMap<>();
        merged.forEach((k, v) -> result.put(k, v[1]/v[0]));

        return result;
    }

    private Map<YearCountry, double[]> average(List<Person> subList) {
        final Map<YearCountry, double[]> result = new HashMap<>();
        for (Person person : subList) {
            final YearCountry bc = new YearCountry(person);
            final double[] buckets = result.computeIfAbsent(bc, unused -> new double[2]);
            buckets[0]++;
            buckets[1] += person.salary();
        }
        return result;
    }

    /**
     * Merges the values in the first map into the second. This method is not thread safe.
     *
     * @param first  map to merge into
     * @param second map to merge from
     */
    private void merge(Map<YearCountry, double[]> first, Map<YearCountry, double[]> second) {
        second.forEach((k, secondBuckets) -> {
            final double[] firstBuckets = first.computeIfAbsent(k, unused -> new double[2]);
            firstBuckets[0] += secondBuckets[0];
            firstBuckets[1] += secondBuckets[1];
        });
    }


}
