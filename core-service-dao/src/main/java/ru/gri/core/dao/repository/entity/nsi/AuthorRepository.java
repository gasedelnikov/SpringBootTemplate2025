package ru.gri.core.dao.repository.entity.nsi;

import org.springframework.stereotype.Repository;
import ru.gri.core.dao.model.nsi.AuthorEntity;
import ru.gri.core.dao.repository.search.SearchRepository;

@Repository
public interface AuthorRepository extends SearchRepository<AuthorEntity, Long> {

}
