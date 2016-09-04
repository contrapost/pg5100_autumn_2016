package org.pg5100.ejb.injection.examples;

import org.pg5100.ejb.injection.InjectorBaseSuite;
import org.pg5100.ejb.injection.Injector;



public class InjectorImpl05Test extends InjectorBaseSuite {

    @Override
    protected Injector getInjector() {
        return new InjectorImp05();
    }
}