package org.pg5100.jpa.relationship;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class MessageWithUserLink extends Message {

    @ManyToOne
    private User sender;

    public MessageWithUserLink(){}

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
