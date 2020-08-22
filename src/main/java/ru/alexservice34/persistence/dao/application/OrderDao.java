package ru.alexservice34.persistence.dao.application;

import ru.alexservice34.persistence.entity.tables.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderDao extends JpaRepository<Order, UUID> {
    List<Order> findAllByUserName(String name);
}
