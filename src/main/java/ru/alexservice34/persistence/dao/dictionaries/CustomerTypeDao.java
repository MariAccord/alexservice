package ru.alexservice34.persistence.dao.dictionaries;

import ru.alexservice34.persistence.entity.dictionaries.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerTypeDao extends JpaRepository<CustomerType, UUID> {

}
