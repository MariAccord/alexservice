package com.example.test_app.dto;

public class ContactDto {
    private CustomerDto customer;
    private String number;
    private String contactType;

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    @Override
    public String toString() {
        return "ContactDto{" +
                "customer=" + customer +
                ", number='" + number + '\'' +
                ", contactType='" + contactType + '\'' +
                '}';
    }
}
