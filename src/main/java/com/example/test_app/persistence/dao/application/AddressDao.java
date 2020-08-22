package com.example.test_app.persistence.dao.application;

import com.example.test_app.persistence.entity.tables.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressDao extends JpaRepository<Address, UUID> {
}
