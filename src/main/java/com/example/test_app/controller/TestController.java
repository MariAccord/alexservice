package com.example.test_app.controller;

import com.example.test_app.dto.ContactDto;
import com.example.test_app.dto.OrderDto;
import com.example.test_app.persistence.dao.dictionaries.*;
import com.example.test_app.persistence.dao.tables.*;
import com.example.test_app.persistence.entity.dictionaries.*;
import com.example.test_app.persistence.entity.tables.*;
import com.example.test_app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private final ExecutedWorkDao executedWorkDao;

    public TestController(ExecutedWorkDao executedWorkDao) {
        this.executedWorkDao = executedWorkDao;
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrderByUserName(
            @RequestParam("username") String userName){
        return Collections.emptyList();
//                repository.getOrdersByUserName(userName);

    }

    @Transactional
    @GetMapping("/ew")
    public List<ExecutedWork> smthmtd() {
        return executedWorkDao.findAll();
    }
}
