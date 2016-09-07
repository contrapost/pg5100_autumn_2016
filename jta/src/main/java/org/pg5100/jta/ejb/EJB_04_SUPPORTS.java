package org.pg5100.jta.ejb;


import org.pg5100.jta.data.Foo;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EJB_04_SUPPORTS {

    @PersistenceContext
    private EntityManager em;


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createFooWithRequiredTransaction(String name){
        Foo foo = new Foo(name);
        em.persist(foo);
    }


    // if a transaction is not needed, for performance one can instruct
    // the container to do not create one. This would had been the default
    // behavior in a REQUIRED
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public boolean isPresentWithSupports(String name){
        return em.find(Foo.class, name) != null;
    }


    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void createFooWithSupports(String name){
        Foo foo = new Foo(name);
        em.persist(foo);
    }



    public void createTwo(String first, String second){
        //here we are in a transaction

        createFooWithRequiredTransaction(first); //in same transaction, no need to create a new one

        //as we are in a transaction, this will work.
        //however, this is regardless of the annotation, as this is a java call from inside the EJB
        createFooWithSupports(second);
    }
}
