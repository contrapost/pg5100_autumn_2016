package org.pg5100.jsf.examples.exp01;

import org.junit.Test;

import static org.junit.Assert.*;


public class CounterBeanTest {

    /*
        The logic inside CounterBean is not relying on any JEE feature,
        so we can test it with just basic unit tests
     */

    @Test
    public void testIncr(){
        CounterBean cb = new CounterBean();
        int x = cb.getCounter();
        cb.increaseCounter();
        int res = cb.getCounter();
        assertEquals(x+1, res);
    }

    @Test
    public void testFailedDecr(){
        CounterBean cb = new CounterBean();
        cb.reset();
        int res = cb.getCounter();
        assertEquals(0, res);
        cb.decreaseCounter();
        res = cb.getCounter();
        assertEquals(0, res);
    }

    @Test
    public void testDecr(){
        CounterBean cb = new CounterBean();
        int x = cb.getCounter();
        cb.increaseCounter();
        assertEquals(x+1, cb.getCounter());
        cb.decreaseCounter();
        assertEquals(x, cb.getCounter());
    }

    @Test
    public void testIncrAndDecr(){
        CounterBean cb = new CounterBean();
        cb.reset(); //0
        cb.decreaseCounter(); //no effect
        cb.increaseCounter();  // 1
        cb.increaseCounter();  // 2
        cb.decreaseCounter(); // 1
        cb.increaseCounter();  // 2
        assertEquals(2, cb.getCounter());
    }
}