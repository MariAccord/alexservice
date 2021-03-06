package ru.alexservice34.persistence.dao.application;

import ru.alexservice34.persistence.entity.tables.ExecutedWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExecutedWorkDao extends JpaRepository<ExecutedWork, UUID> {
}
