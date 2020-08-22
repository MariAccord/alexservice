package ru.alexservice34.persistence.dao.dictionaries;

import ru.alexservice34.persistence.entity.dictionaries.LegalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LegalTypeDao extends JpaRepository<LegalType, UUID> {
}
