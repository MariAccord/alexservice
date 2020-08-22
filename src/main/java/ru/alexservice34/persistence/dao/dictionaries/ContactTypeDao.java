package ru.alexservice34.persistence.dao.dictionaries;

import ru.alexservice34.persistence.entity.dictionaries.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactTypeDao extends JpaRepository<ContactType, UUID> {
}
