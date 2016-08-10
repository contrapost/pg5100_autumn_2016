package org.pg5100.jpa.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class WriteReadTest {

    private EntityManagerFactory factory;
    private EntityManager em;

    @Before
    public void init() {
        factory = Persistence.createEntityManagerFactory("DB");
        em = factory.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        factory.close();
    }

    @Test
    public void testWriteReadEntities(){

        String first = "first";
        String second = "second";

        User01 a = new User01();
        a.setName(first);

        User01 b = new User01();
        b.setName(second);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            em.persist(a);
            em.persist(b);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            fail();
        }

        //just to confirm we read from DB and not a cache, create a new EntityManager
        em.close();
        em = factory.createEntityManager();

        //query the DB to check if data was indeed inserted
        Query query = em.createQuery("SELECT k FROM User01 k");
        List<User01> users = query.getResultList();

        assertEquals(2 , users.size());
        assertTrue(users.stream().anyMatch(k -> k.getName().equals(first)));
        assertTrue(users.stream().anyMatch(k -> k.getName().equals(second)));
    }
}
