package ru.gri.core.security.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.gri.core.security.dto.JwtAuthenticationResponse;
import ru.gri.core.security.dto.JwtLoginRequest;
import ru.gri.core.security.dto.UserWithSecurity;

public interface AuthenticationService {

    JwtAuthenticationResponse refreshToken(HttpServletRequest request, UserWithSecurity user);

    JwtAuthenticationResponse login(JwtLoginRequest request);
}
