package org.pg5100.jta.ejb;

import org.junit.Test;

import static org.junit.Assert.*;

public class EJB_11_exceptionsTest extends TestBase{

    @Test
    public void testAddAndThrowRuntimeException() throws Exception {
        EJB_11_exceptions ejb = getEJB(EJB_11_exceptions.class);

        String name = "Bar";

        assertFalse(queriesEJB.isInDB(name));

        try {
            ejb.addAndThrowRuntimeException(name);
            fail();
        } catch (RuntimeException e){
        }

        //rollback
        assertFalse(queriesEJB.isInDB(name));
    }

    @Test
    public void testAddAndThrowException() throws Exception {
        EJB_11_exceptions ejb = getEJB(EJB_11_exceptions.class);

        String name = "Bar";

        assertFalse(queriesEJB.isInDB(name));

        try {
            ejb.addAndThrowException(name);
            fail();
        } catch (Exception e){
        }

        // no rollback
        assertTrue(queriesEJB.isInDB(name));
    }

    @Test
    public void testAddAndThrowRuntimeExceptionNoRollback() throws Exception {
        EJB_11_exceptions ejb = getEJB(EJB_11_exceptions.class);

        String name = "Bar";

        assertFalse(queriesEJB.isInDB(name));

        try {
            ejb.addAndThrowRuntimeExceptionNoRollback(name);
            fail();
        } catch (RuntimeException e){
        }

        // no rollback
        assertTrue(queriesEJB.isInDB(name));
    }

    @Test
    public void testAddAndThrowExceptionWithRollback() throws Exception {
        EJB_11_exceptions ejb = getEJB(EJB_11_exceptions.class);

        String name = "Bar";

        assertFalse(queriesEJB.isInDB(name));

        try {
            ejb.addAndThrowExceptionWithRollback(name);
            fail();
        } catch (Exception e){
        }

        // rollback
        assertFalse(queriesEJB.isInDB(name));
    }
}