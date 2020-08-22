package ru.alexservice34.persistence.dao.application;

import ru.alexservice34.persistence.entity.tables.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactDao extends JpaRepository<Contact, UUID> {
}
