package org.pg5100.ejb.injection.examples;

import org.pg5100.ejb.injection.Injector;

public class InjectorImp03 implements Injector {


    public <T> T createInstance(Class<T> klass) throws IllegalArgumentException {

        if(klass == null){
            throw new IllegalArgumentException("Null input");
        }

        T t;

        try {
            t = klass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to instantiate object for "+klass.getName()+" : "+e.getMessage());
        }

        return t;
    }
}
