package org.pg5100.jpa.lock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

import java.util.function.Consumer;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class UserTest {

    private EntityManagerFactory factory;

    @Before
    public void init() {
        factory = Persistence.createEntityManagerFactory("DB");
    }

    @After
    public void tearDown() {
        factory.close();
    }


    private Long createUser(String name){
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        User user = new User();
        user.setName(name);

        tx.begin();
        em.persist(user);
        tx.commit();

        return user.getId();
    }

    @Test
    public void testVersionIncrement(){

        String name = "name";

        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        User user = new User();
        user.setName(name);

        tx.begin();
        em.persist(user);
        tx.commit();
        int x = user.getVersion();

        tx.begin();
        user.setName("foo");
        em.merge(user);
        tx.commit();
        int y = user.getVersion();

        assertEquals(x + 1, y);


        tx.begin();
        //no modification of user here
        em.merge(user);
        tx.commit();
        int z = user.getVersion();

        assertEquals(y, z); // version has not been increased
    }



    @Test
    public void testOptimistic(){

        String name = "optimistic";

        long id = createUser(name);

        EntityManager e1 = factory.createEntityManager();
        EntityTransaction t1 = e1.getTransaction();

        t1.begin();
        User u1 = e1.find(User.class, id,
                LockModeType.OPTIMISTIC); //if @Version is present, OPTIMISTIC is the default anyway
        assertNotNull(u1);

        //check that the user with the given name is in the database
        Query query = e1.createQuery("select count(u) from User u where u.name = '"+name+"'");
        long res = (Long)query.getSingleResult();
        assertEquals(1 , res);

        //do a sync update on a new thread
        SyncExecutor executor = new SyncExecutor(factory);
        executor.syncExe(em -> {
            User user = em.find(User.class, id);
            user.setName("foo");
        });

        //name has been changed now
        res = (Long)query.getSingleResult();
        assertEquals(0 , res);

        try {
            t1.commit();
            fail();
        } catch (Exception e){
            /*
                This is expected: u1 is in a stale state inside the cache e1.
                When we try to commit, JPA detects this fact due to the optimistic lock,
                and so throws an exception
             */
        }
    }



}
