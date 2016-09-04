package org.pg5100.ejb.framework.injection.data;

import org.pg5100.ejb.framework.injection.AnnotatedForInjection;

public class CompositeInjectionClass {

    @AnnotatedForInjection
    private BasicInjectionClass basicInjectionClass;


    public BasicInjectionClass getBasicInjectionClass() {
        return basicInjectionClass;
    }
}
