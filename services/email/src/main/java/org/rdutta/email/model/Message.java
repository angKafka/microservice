package org.rdutta.email.model;

import java.util.List;

public class Message {
    private String subject;
    private Body body;
    private List<ToRecipients> toRecipients;


    public Message(){}
    public Message(String subject, Body body, List<ToRecipients> toRecipients) {
        this.subject = subject;
        this.body = body;
        this.toRecipients = toRecipients;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public List<ToRecipients> getToRecipients() {
        return toRecipients;
    }

    public void setToRecipients(List<ToRecipients> toRecipients) {
        this.toRecipients = toRecipients;
    }

}
