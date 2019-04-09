package com.speedment.evaluation.declarative;

import com.speedment.evaluation.Person;
import com.speedment.evaluation.Rest;
import com.speedment.evaluation.RestUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public final class RestDeclarative implements Rest {

    @Override
    public List<Person> page(List<Person> persons, Predicate<Person> predicate, Comparator<Person> order, int page) {
        return persons.stream()
            .filter(predicate)
            .sorted(order)
            .skip(RestUtil.PAGE_SIZE * (long) page)
            .limit(RestUtil.PAGE_SIZE)
            .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }
}
