package ru.alexservice34.controller;

import ru.alexservice34.dto.customersearch.CustomerSearchRequest;
import ru.alexservice34.dto.customersearch.CustomerSearchResponse;
import ru.alexservice34.service.CustomerService;
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
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
