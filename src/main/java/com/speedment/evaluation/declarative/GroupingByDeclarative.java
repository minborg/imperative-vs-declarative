package com.speedment.evaluation.declarative;

import com.speedment.evaluation.YearCountry;
import com.speedment.evaluation.GroupingBy;
import com.speedment.evaluation.Person;

import java.util.Collection;
import java.util.Map;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

public class GroupingByDeclarative implements GroupingBy {

    @Override
    public Map<YearCountry, Double> average(Collection<Person> persons) {
        return persons.parallelStream()
            .collect(
                groupingBy(YearCountry::new, averagingDouble(Person::salary))
            );
    }
}
