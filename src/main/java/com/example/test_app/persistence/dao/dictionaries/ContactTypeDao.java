package com.example.test_app.persistence.dao.dictionaries;

import com.example.test_app.persistence.entity.dictionaries.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactTypeDao extends JpaRepository<ContactType, UUID> {
}
