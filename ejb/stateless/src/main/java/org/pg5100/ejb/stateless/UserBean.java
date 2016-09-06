package org.pg5100.ejb.stateless;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserBean {

    //Dependency injection: the container will add it
    @PersistenceContext
    private EntityManager em;

    public UserBean(){}

    //all methods in a EJB are wrapped in a transaction, with automated rollback if exceptions
    public void registerNewUser(String userId, String name, String surname){
        if(isRegistered(userId)){
            return;
        }

        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setSurname(surname);

        em.persist(user);
    }

    public boolean isRegistered(String userId){
        User user = em.find(User.class, userId);
        return user != null;
    }

    public long getNumberOfUsers(){
        Query query = em.createQuery("select count(u) from User u");
        long n = (Long) query.getSingleResult();
        return n;
    }
}
