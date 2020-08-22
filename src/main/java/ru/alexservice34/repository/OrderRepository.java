package ru.alexservice34.repository;

import ru.alexservice34.dto.OrderDto;

import java.util.List;

public interface OrderRepository {
    List<OrderDto> getOrdersByUserName(String userName);

}
