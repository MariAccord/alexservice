package com.example.test_app.persistence.dao.dictionaries;

import com.example.test_app.persistence.entity.dictionaries.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerTypeDao extends JpaRepository<CustomerType, UUID> {

}
