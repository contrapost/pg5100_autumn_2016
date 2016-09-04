package org.pg5100.ejb.injection;

import org.junit.Test;
import org.pg5100.ejb.injection.data.BasicInjectionClass;
import org.pg5100.ejb.injection.data.CompositeInjectionClass;
import org.pg5100.ejb.injection.data.EmptyClass;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


/**
 * Using an abstract class to define a set of tests for the interface Injector.
 */
public abstract class InjectorBaseSuite {

    /**
     * Method used by all subclass test suites to specify which injector they are testing.
     *
     * Note: as some of the test suites do fail (and they are expected to fail), their name
     * ends with "*TestNot", so that Maven will not execute them when compiling/packaging
     * from command line (eg "mvn verify")
     *
     */
    protected abstract Injector getInjector();



    @Test (expected = IllegalArgumentException.class)
    public void testNullClass(){
        Injector injector = getInjector();
        injector.createInstance(null);
    }

    @Test
    public void testString(){
        Injector injector = getInjector();
        String s = injector.createInstance(String.class);
        assertNotNull(s);
    }

    @Test
    public void testEmptyClass(){
        Injector injector = getInjector();
        EmptyClass emptyClass = injector.createInstance(EmptyClass.class);
        assertNotNull(emptyClass);
    }

    @Test
    public void testBasicInjectionClass(){
        Injector injector = getInjector();
        BasicInjectionClass basicInjectionClass = injector.createInstance(BasicInjectionClass.class);
        assertNotNull(basicInjectionClass);

        EmptyClass injected = basicInjectionClass.getInjectedEmptyClass();
        assertNotNull(injected);

        EmptyClass nonInjected = basicInjectionClass.getNonInjectedEmptyClass();
        assertNull(nonInjected); //this should have been left null
    }

    @Test
    public void testCompositeInjectionClass(){
        Injector injector = getInjector();
        CompositeInjectionClass compositeInjectionClass = injector.createInstance(CompositeInjectionClass.class);
        assertNotNull(compositeInjectionClass);

        BasicInjectionClass injectedBasicInjectionClass = compositeInjectionClass.getBasicInjectionClass();
        assertNotNull(injectedBasicInjectionClass);

        EmptyClass injectedEmptyClass = injectedBasicInjectionClass.getInjectedEmptyClass();
        assertNotNull(injectedEmptyClass);
    }


    /*
        Many more cases, like:
        - arrays
        - primitive values
        - interfaces
        - etc.
     */
}
