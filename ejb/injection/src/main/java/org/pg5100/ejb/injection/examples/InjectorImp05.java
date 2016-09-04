package org.pg5100.ejb.injection.examples;

import org.pg5100.ejb.injection.AnnotatedForInjection;
import org.pg5100.ejb.injection.Injector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class InjectorImp05 implements Injector {


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


        for(Field field : klass.getDeclaredFields()){

            Annotation annotation = field.getAnnotation(AnnotatedForInjection.class);
            if(annotation==null){
                //skip, as field was not marked for injection
                continue;
            }

            try {
                Class<?> typeToInject = field.getType();
                Object objectToInject = createInstance(typeToInject); //Beware infinite recursion...
                field.setAccessible(true); //needed, otherwise fails on private fields
                field.set(t, objectToInject);
            } catch (Exception e){
                throw new IllegalArgumentException("Not possible to inject "+field.getName()+" due to: "+e.getMessage());
            }
        }

        return t;
    }
}
