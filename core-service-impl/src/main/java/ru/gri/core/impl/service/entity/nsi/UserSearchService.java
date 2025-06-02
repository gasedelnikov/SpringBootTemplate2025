package ru.gri.core.impl.service.entity.nsi;

import ru.gri.core.impl.service.entity.SearchService;
import ru.gri.core.model.dto.User;
import ru.gri.core.model.dto.out.nsi.UserOut;

public interface UserSearchService extends SearchService<Long, UserOut> {

    User findByLogin(String login);
}