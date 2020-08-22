package ru.alexservice34.persistence.entity.tables;

import ru.alexservice34.persistence.entity.dictionaries.CustomerType;
import ru.alexservice34.persistence.entity.dictionaries.LegalType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer", schema = "application")
public class Customer {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    private String email;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_type_id", nullable = false)
    private CustomerType customerType;

    @ManyToOne
    @JoinColumn(name = "legal_type_id", nullable = false)
    private LegalType legalType;

    @NotNull
    @Size(min = 2, max = 100)
    private String comment;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Address> addresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Contact> contacts;



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

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public LegalType getLegalType() {
        return legalType;
    }

    public void setLegalType(LegalType legalType) {
        this.legalType = legalType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
