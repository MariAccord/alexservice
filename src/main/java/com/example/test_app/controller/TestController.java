package com.example.test_app.controller;

import com.example.test_app.dto.OrderDto;
import com.example.test_app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private OrderRepository repository;

    @Autowired
    public TestController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrderByUserName(
            @RequestParam("username") String userName){
        return repository.getOrdersByUserName(userName);
    }
}
