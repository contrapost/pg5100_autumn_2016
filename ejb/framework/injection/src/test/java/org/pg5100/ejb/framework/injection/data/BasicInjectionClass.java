package org.pg5100.ejb.framework.injection.data;

import org.pg5100.ejb.framework.injection.AnnotatedForInjection;

public class BasicInjectionClass {

    private EmptyClass nonInjectedEmptyClass;

    @AnnotatedForInjection
    private EmptyClass injectedEmptyClass;


    public EmptyClass getNonInjectedEmptyClass() {
        return nonInjectedEmptyClass;
    }

    public EmptyClass getInjectedEmptyClass() {
        return injectedEmptyClass;
    }
}
