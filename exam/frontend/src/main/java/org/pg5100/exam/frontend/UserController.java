package org.pg5100.exam.frontend;

import org.pg5100.exam.backend.ejb.UserEJB;
import org.pg5100.exam.backend.entity.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UserController {

    @EJB
    private UserEJB userEJB;

    public User getUser(String userId){

        return userEJB.getUser(userId);
    }

    public long getKarma(String userId){
        return  userEJB.computeKarma(userId);
    }
}
