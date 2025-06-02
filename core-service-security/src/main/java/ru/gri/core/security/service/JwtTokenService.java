package ru.gri.core.security.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenService {

    String getTokenFromRequest(@NonNull HttpServletRequest request);

    String extractUserName(String token);

    String generateAccessToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
