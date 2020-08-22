package ru.alexservice34.persistence.dao.application;

import ru.alexservice34.persistence.entity.tables.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerDao extends JpaRepository<Customer, UUID> {
}
