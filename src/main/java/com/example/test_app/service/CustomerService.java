package com.example.test_app.service;

import com.example.test_app.persistence.dao.tables.CustomerDao;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


}
