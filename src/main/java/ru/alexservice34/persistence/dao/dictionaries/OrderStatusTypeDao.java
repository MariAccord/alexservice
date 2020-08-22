package ru.alexservice34.persistence.dao.dictionaries;

import ru.alexservice34.persistence.entity.dictionaries.OrderStatysType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderStatusTypeDao extends JpaRepository<OrderStatysType, UUID> {
}
