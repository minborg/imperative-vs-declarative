package com.speedment.evaluation;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface Rest {

    /**
     * Returns an unmodifiable list from the given parameters.
     *
     * @param persons as the raw input list
     * @param predicate to select which elements to include
     * @param order in which to present persons
     * @param page to show. 0 is the first page
     * @return an unmodifiable list from the given parameters
     */
    List<Person> page(List<Person> persons, Predicate<Person> predicate, Comparator<Person> order, int page);
}
