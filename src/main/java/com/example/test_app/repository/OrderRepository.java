package com.example.test_app.repository;

import com.example.test_app.dto.OrderDto;

import java.util.List;

public interface OrderRepository {
    List<OrderDto> getOrdersByUserName(String userName);

}
