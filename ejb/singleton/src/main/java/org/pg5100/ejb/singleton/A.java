package org.pg5100.ejb.singleton;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class A {

    //dependency injection
    @EJB
    private Counter counter;

    public void incrementCounter(){
        counter.increment();
    }
}
