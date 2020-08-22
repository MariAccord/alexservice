package com.example.test_app.persistence.dao.application;

import com.example.test_app.persistence.entity.tables.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderDao extends JpaRepository<Order, UUID> {
    List<Order> findAllByUserName(String name);
}
