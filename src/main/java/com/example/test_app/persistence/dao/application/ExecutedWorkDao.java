package com.example.test_app.persistence.dao.application;

import com.example.test_app.persistence.entity.tables.ExecutedWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExecutedWorkDao extends JpaRepository<ExecutedWork, UUID> {
}
