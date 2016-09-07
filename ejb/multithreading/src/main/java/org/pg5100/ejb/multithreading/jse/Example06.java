package org.pg5100.ejb.multithreading.jse;

import org.pg5100.ejb.multithreading.Counter;

import java.util.concurrent.atomic.AtomicInteger;

public class Example06 implements Counter {

    private final AtomicInteger x = new AtomicInteger(0);

    @Override
    public void incrementCounter() {
        x.incrementAndGet();
    }

    @Override
    public int getCounter() {
        return x.get();
    }
}