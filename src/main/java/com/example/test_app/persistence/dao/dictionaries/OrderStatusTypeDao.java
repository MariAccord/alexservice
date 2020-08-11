package com.example.test_app.persistence.dao.dictionaries;

import com.example.test_app.persistence.entity.dictionaries.OrderStatysType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderStatusTypeDao extends JpaRepository<OrderStatysType, UUID> {
}
