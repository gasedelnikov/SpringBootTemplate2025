package ru.gri.core.security.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.gri.core.security.dto.JwtAuthenticationResponse;
import ru.gri.core.security.dto.JwtLoginRequest;
import ru.gri.core.security.dto.UserWithSecurity;
import ru.gri.core.security.service.AuthenticationService;
import ru.gri.core.security.service.JwtTokenService;
import ru.gri.core.security.service.UserSecurityService;


@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserSecurityService userSecurityService;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(UserSecurityService userSecurityService,
                                     JwtTokenService jwtTokenService,
                                     AuthenticationManager authenticationManager) {
        this.userSecurityService = userSecurityService;
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationResponse refreshToken(HttpServletRequest request, UserWithSecurity user) {
        return JwtAuthenticationResponse.builder()
                .accessToken(jwtTokenService.generateAccessToken(user))
                .refreshToken(jwtTokenService.getTokenFromRequest(request))
                .build();
    }

    public JwtAuthenticationResponse login(JwtLoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserWithSecurity user = userSecurityService.loadUserByUsername(request.getUsername());

        return JwtAuthenticationResponse.builder()
                .accessToken(jwtTokenService.generateAccessToken(user))
                .refreshToken(jwtTokenService.generateRefreshToken(user))
                .build();
    }
}
