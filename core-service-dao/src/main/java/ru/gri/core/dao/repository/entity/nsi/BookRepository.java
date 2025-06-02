package ru.gri.core.dao.repository.entity.nsi;

import org.springframework.stereotype.Repository;
import ru.gri.core.dao.model.nsi.BookEntity;
import ru.gri.core.dao.repository.search.SearchRepository;

@Repository
public interface BookRepository extends SearchRepository<BookEntity, Long> {

}
