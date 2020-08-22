package ru.alexservice34.persistence.dao.application;

import ru.alexservice34.persistence.entity.tables.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressDao extends JpaRepository<Address, UUID> {
}
