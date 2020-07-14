package com.example.test_app.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderDto {
    private UUID id;
    private CustomerDto customer;
    private LocalDateTime dateCreated;
    private String problemDescription;
    private String userName;
    private List<OrderStatusDto> orderStatuses;
    private List<ExecutedWorkDto> executedWorks;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<OrderStatusDto> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatusDto> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public List<ExecutedWorkDto> getExecutedWorks() {
        return executedWorks;
    }

    public void setExecutedWorks(List<ExecutedWorkDto> executedWorks) {
        this.executedWorks = executedWorks;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", customer=" + customer +
                ", dateCreated=" + dateCreated +
                ", problemDescription='" + problemDescription + '\'' +
                ", userName='" + userName + '\'' +
                ", orderStatuses=" + orderStatuses +
                ", executedWorks=" + executedWorks +
                '}';
    }
}
