package ru.alexservice34.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alexservice34.dto.OrderDto;
import ru.alexservice34.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getbyuser")
    public List<OrderDto> getOrderByUser(
            @RequestParam("username") String userName) {
        return orderService.getOrderByUserName(userName);
    }

}
