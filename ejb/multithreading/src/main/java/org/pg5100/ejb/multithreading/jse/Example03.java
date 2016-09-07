package org.pg5100.ejb.multithreading.jse;

import org.pg5100.ejb.multithreading.Counter;

public class Example03 implements Counter {


    private volatile int x;

    @Override
    public void incrementCounter() {
        synchronized (this) {
            x = x + 1;
        }
    }

    @Override
    public int getCounter() {
        return x;
    }
}
