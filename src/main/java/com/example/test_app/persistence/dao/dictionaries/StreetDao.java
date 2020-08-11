package com.example.test_app.persistence.dao.dictionaries;

import com.example.test_app.persistence.entity.dictionaries.Street;
import com.example.test_app.persistence.entity.dictionaries.StreetEmbedded;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetDao extends JpaRepository<Street, StreetEmbedded> {

}
