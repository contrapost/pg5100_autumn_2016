package org.pg5100.ejb.multithreading.jse;

import org.pg5100.ejb.multithreading.Counter;

public class Example05 implements Counter {

    private volatile int x;

    @Override
    public synchronized void incrementCounter() {
        x = x + 1;
    }

    @Override
    public int getCounter() {
        return x;
    }
}