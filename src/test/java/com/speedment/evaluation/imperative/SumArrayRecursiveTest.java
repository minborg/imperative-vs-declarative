package com.speedment.evaluation.imperative;

import com.speedment.evaluation.AbstractSumArrayTest;
import com.speedment.evaluation.imperative.SumArrayImperative;
import com.speedment.evaluation.imperative.SumArrayRecursive;
import org.junit.jupiter.api.Disabled;

@Disabled("This will create a StackOverflowError")
final class SumArrayRecursiveTest extends AbstractSumArrayTest {

    SumArrayRecursiveTest() {
        super(SumArrayRecursive::new);
    }
}
