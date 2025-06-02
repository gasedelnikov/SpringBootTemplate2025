package ru.gri.core.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.gri.core.security.service.JwtTokenService;
import ru.gri.core.security.service.UserSecurityService;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserSecurityService userSecurityService;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenService jwtTokenService, UserSecurityService userSecurityService) {
        this.jwtTokenService = jwtTokenService;
        this.userSecurityService = userSecurityService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        boolean isPublicUrl = SecurityConfiguration.PUBLIC_URLS.matcher(request).isMatch();
        log.debug("JWT Authentication Filter; isPublicUrl = {}", isPublicUrl);

        if (!isPublicUrl) {
            doJwt(request);
        }

        filterChain.doFilter(request, response);
    }

    private void doJwt(HttpServletRequest request) {
        String jwt = jwtTokenService.getTokenFromRequest(request);
        if (jwt == null) {
            return;
        }

        var username = jwtTokenService.extractUserName(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userSecurityService.loadUserByUsername(username);

            if (jwtTokenService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
    }
}
