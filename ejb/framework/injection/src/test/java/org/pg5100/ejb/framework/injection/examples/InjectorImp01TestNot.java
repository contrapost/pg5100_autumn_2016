package org.pg5100.ejb.framework.injection.examples;

import org.pg5100.ejb.framework.injection.InjectorBaseSuite;
import org.pg5100.ejb.framework.injection.Injector;


public class InjectorImp01TestNot extends InjectorBaseSuite {

    @Override
    protected Injector getInjector() {
        return new InjectorImp01();
    }
}