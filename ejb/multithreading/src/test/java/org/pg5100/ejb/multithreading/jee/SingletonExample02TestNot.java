package org.pg5100.ejb.multithreading.jee;

import org.pg5100.ejb.multithreading.CounterTestBaseJEE;

public class SingletonExample02TestNot extends CounterTestBaseJEE {

    @Override
    protected Class<?> getSingletonClass() {
        return SingletonExample02.class;
    }
}