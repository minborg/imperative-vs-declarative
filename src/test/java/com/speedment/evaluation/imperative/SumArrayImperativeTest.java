package com.speedment.evaluation.imperative;

import com.speedment.evaluation.AbstractSumArrayTest;
import com.speedment.evaluation.declarative.SumArrayDeclarative;

final class SumArrayImperativeTest extends AbstractSumArrayTest {

    SumArrayImperativeTest() {
        super(SumArrayImperative::new);
    }
}
