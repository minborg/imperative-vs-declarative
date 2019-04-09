package com.speedment.evaluation.imperative;

import com.speedment.evaluation.Person;
import com.speedment.evaluation.Rest;
import com.speedment.evaluation.RestUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Collections.unmodifiableList;

public final class RestImperative implements Rest {

    @Override
    public List<Person> page(List<Person> persons, Predicate<Person> predicate, Comparator<Person> order, int page) {
        final List<Person> list = new ArrayList<>();
        for (Person person:persons) {
            if (predicate.test(person)) {
                list.add(person);
            }
        }
        list.sort(order);
        final int from = RestUtil.PAGE_SIZE * page;
        if (list.size()<= from) {
            return Collections.emptyList();
        }
        return unmodifiableList(list.subList(from, Math.min(list.size(), from + RestUtil.PAGE_SIZE)));
    }
}
