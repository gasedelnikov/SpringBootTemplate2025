package ru.gri.core.security.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.gri.core.security.dto.UserWithSecurity;

public interface UserSecurityService extends UserDetailsService {

    UserWithSecurity loadUserByUsername(String username);

}
