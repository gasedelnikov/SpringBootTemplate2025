package ru.gri.core.dao.repository.entity.nsi;

import org.springframework.stereotype.Repository;
import ru.gri.core.dao.model.nsi.UserEntity;
import ru.gri.core.dao.repository.search.SearchRepository;

@Repository
public interface UserRepository extends SearchRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);
}
