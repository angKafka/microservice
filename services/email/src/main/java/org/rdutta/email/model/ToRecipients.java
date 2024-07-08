package org.rdutta.email.model;

public class ToRecipients {
    private EmailAddress emailAddress;

    public ToRecipients() {}

    public ToRecipients(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }
}
