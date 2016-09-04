package org.pg5100.ejb.injection.data;

import org.pg5100.ejb.injection.AnnotatedForInjection;

public class CompositeInjectionClass {

    @AnnotatedForInjection
    private BasicInjectionClass basicInjectionClass;


    public BasicInjectionClass getBasicInjectionClass() {
        return basicInjectionClass;
    }
}
