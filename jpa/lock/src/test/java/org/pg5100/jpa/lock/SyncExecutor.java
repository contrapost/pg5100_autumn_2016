package org.pg5100.jpa.lock;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.function.Consumer;

public class SyncExecutor {

    private final  EntityManagerFactory factory;

    public SyncExecutor(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void syncExe(Consumer<EntityManager> command) {

        //create a thread
        Thread t = new Thread(() ->{
            EntityManager em = factory.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            try{
                command.accept(em);
                tx.commit();
            } catch (Exception e){
                tx.rollback();
                System.out.println("\n\nFailed transaction on separated thread: "+e.getCause().toString()+"\n\n");
            }
            em.close();
        });

       startAndWait(t);
    }

    private void startAndWait(Thread t){
        //start the thread
        t.start();

        //but then wait for when it is finished
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
