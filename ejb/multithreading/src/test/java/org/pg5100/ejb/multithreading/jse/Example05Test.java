package org.pg5100.ejb.multithreading.jse;

import org.pg5100.ejb.multithreading.Counter;
import org.pg5100.ejb.multithreading.CounterTestBase;

public class Example05Test extends CounterTestBase {

    @Override
    protected Counter getCounter() {
        return new Example05();
    }
}