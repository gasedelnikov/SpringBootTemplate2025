package ru.gri.core.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gri.core.impl.service.entity.nsi.UserSearchService;
import ru.gri.core.model.dto.User;
import ru.gri.core.model.error.CustomExceptionMessage;
import ru.gri.core.model.exception.CustomException;
import ru.gri.core.security.dto.UserWithSecurity;
import ru.gri.core.security.service.UserSecurityService;

@Slf4j
@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    private final UserSearchService userService;

    @Autowired
    public UserSecurityServiceImpl(UserSearchService userService) {
        this.userService = userService;
    }

    @Override
    public UserWithSecurity loadUserByUsername(String username) {
        User user = userService.findByLogin(username);
        if (user == null) {
            throw new CustomException(CustomExceptionMessage.BAD_CREDENTIALS);
        }

        return new UserWithSecurity(user);
    }

}
