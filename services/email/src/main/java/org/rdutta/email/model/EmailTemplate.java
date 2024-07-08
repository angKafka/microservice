package org.rdutta.email.model;

public class EmailTemplate {
    private Message message;
    private String  saveToSentItems;

    public EmailTemplate() {
    }

    public EmailTemplate(Message message, String saveToSentItems) {
        this.message = message;
        this.saveToSentItems = saveToSentItems;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getSaveToSentItems() {
        return saveToSentItems;
    }

    public void setSaveToSentItems(String saveToSentItems) {
        this.saveToSentItems = saveToSentItems;
    }
}
