package com.example.test_app.controller;

import com.example.test_app.dto.CustomerDto;
import com.example.test_app.dto.customersearch.CustomerSearchRequest;
import com.example.test_app.dto.customersearch.CustomerSearchResponse;
import com.example.test_app.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/searchorcreate")
    public ResponseEntity<CustomerSearchResponse> searchOrCreate(
            @RequestBody CustomerSearchRequest request){
        System.out.println(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
