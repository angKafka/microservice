package org.rdutta.email.model;

public class EmailAddress {
    private String address;

    public EmailAddress() {}

    public EmailAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
