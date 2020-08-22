package ru.alexservice34.persistence.dao.dictionaries;

import ru.alexservice34.persistence.entity.dictionaries.Street;
import ru.alexservice34.persistence.entity.dictionaries.StreetEmbedded;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetDao extends JpaRepository<Street, StreetEmbedded> {

}
