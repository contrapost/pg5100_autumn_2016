package org.pg5100.ejb.injection.examples;

import org.pg5100.ejb.injection.InjectorBaseSuite;
import org.pg5100.ejb.injection.Injector;


public class InjectorImp02TestNot extends InjectorBaseSuite {

    @Override
    protected Injector getInjector() {
        return new InjectorImp02();
    }
}