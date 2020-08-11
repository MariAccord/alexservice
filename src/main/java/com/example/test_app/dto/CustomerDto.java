package com.example.test_app.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerDto {
    private UUID id;
    private String name;
    private String email;
    private String customerType;
    private String legalType;
    private String comment;
    private List<AddressDto> addresses = new ArrayList<>();
    private List<ContactDto> contacts = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getLegalType() {
        return legalType;
    }

    public void setLegalType(String legalType) {
        this.legalType = legalType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDto> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", customerType='" + customerType + '\'' +
                ", legalType='" + legalType + '\'' +
                ", comment='" + comment + '\'' +
                ", addresses=" + addresses +
                ", contacts=" + contacts +
                '}';
    }
}
