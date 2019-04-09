package com.speedment.evaluation.imperative;

import com.speedment.evaluation.YearCountry;
import com.speedment.evaluation.GroupingBy;
import com.speedment.evaluation.Person;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;

public class GroupingByImperative implements GroupingBy {

    @Override
    public Map<YearCountry, Double> average(Collection<Person> persons) {
        final List<Person> personList = new ArrayList<>(persons);
        final int threads = ForkJoinPool.commonPool().getParallelism();
        final int step = personList.size() / threads;

        // Divide the work into smaller work items
        final List<List<Person>> subLists = new ArrayList<>();
        for (int i = 0; i < threads - 1; i++) {
            subLists.add(personList.subList(i * step, (i + 1) * step));
        }
        subLists.add(personList.subList((threads - 1) * step, personList.size()));


        final ConcurrentMap<YearCountry, AverageAccumulator> accumulators = new ConcurrentHashMap<>();
        // Submit the work items to the common ForkJoinPool
        final List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            final List<Person> subList = subLists.get(i);
            futures.add(CompletableFuture.runAsync(() -> average(subList, accumulators)));
        }

        // Wait for completion
        for (int i = 0; i < threads; i++) {
            futures.get(i).join();
        }

        // Construct the result
        final Map<YearCountry, Double> result = new HashMap<>();
        accumulators.forEach((k, v) -> result.put(k, v.average()));

        return result;
    }

    private void average(List<Person> subList, ConcurrentMap<YearCountry, AverageAccumulator> accumulators) {
        for (Person person : subList) {
            final YearCountry bc = new YearCountry(person);
            accumulators.computeIfAbsent(bc, unused -> new AverageAccumulator())
                .add(person.salary());
        }
    }

    private final class AverageAccumulator {
        int count;
        double sum;

        synchronized void add(double term) {
            count++;
            sum += term;
        }

        double average() {
            return sum / count;
        }
    }

}
