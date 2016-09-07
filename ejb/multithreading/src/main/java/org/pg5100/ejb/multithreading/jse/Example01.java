package org.pg5100.ejb.multithreading.jse;

import org.pg5100.ejb.multithreading.Counter;

public class Example01 implements Counter{

    private int x;

    @Override
    public void incrementCounter() {
        x = x + 1;
    }

    @Override
    public int getCounter() {
        return x;
    }
}
