package ru.alexservice34.controller;

import ru.alexservice34.dto.OrderDto;
import ru.alexservice34.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private final OrderService orderService;

    public TestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrderByUser(
            @RequestParam("username") String userName) {
        return orderService.getOrderByUserName(userName);
    }

}
