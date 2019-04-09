package com.speedment.evaluation;

import java.util.Collection;
import java.util.Map;

public interface GroupingBy {

    Map<YearCountry, Double> average(Collection<Person> persons);

}
