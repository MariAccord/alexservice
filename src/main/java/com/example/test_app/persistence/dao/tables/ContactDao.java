package com.example.test_app.persistence.dao.tables;

import com.example.test_app.persistence.entity.tables.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactDao extends JpaRepository<Contact, UUID> {
}
