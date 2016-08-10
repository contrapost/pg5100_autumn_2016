package org.pg5100.jpa.table;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InsertTest {

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

    private boolean persistInATransaction(Object obj) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(obj);
            tx.commit();
        } catch (Exception e) {
            System.out.println("FAILED TRANSACTION: " + e.toString());
            tx.rollback();
            return false;
        }

        return true;
    }


    @Test
    public void insertMovie() {

        MovieDetails movie = new MovieDetails();
        movie.setId(1L);

        boolean persisted = persistInATransaction(movie);
        assertTrue(persisted);
    }

    @Test
    public void insertSong() {

        Song song = new Song();
        song.setId(2L);

        boolean persisted = persistInATransaction(song);
        assertFalse(persisted); // no table called "Song" in the DB
    }

}
