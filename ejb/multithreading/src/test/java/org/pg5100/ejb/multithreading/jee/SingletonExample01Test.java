package org.pg5100.ejb.multithreading.jee;

import org.pg5100.ejb.multithreading.CounterTestBaseJEE;

public class SingletonExample01Test extends CounterTestBaseJEE {

    @Override
    protected Class<?> getSingletonClass() {
        return SingletonExample01.class;
    }
}