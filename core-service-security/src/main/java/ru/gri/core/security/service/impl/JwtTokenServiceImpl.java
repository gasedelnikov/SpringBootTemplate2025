package ru.gri.core.security.service.impl;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.gri.core.impl.service.date.DateService;
import ru.gri.core.security.dto.UserWithSecurity;
import ru.gri.core.security.service.JwtTokenService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtTokenServiceImpl implements JwtTokenService {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";

    private final DateService dateService;
    private final String issuer;
    private final int expirationAccessSec;
    private final int expirationRefreshSec;
    private final SecretKey secretKey;

    @Autowired
    public JwtTokenServiceImpl(DateService dateService,
                               @Value("${jwt.issuer}") final String issuer,
                               @Value("${jwt.expiration-access-sec}") final int expirationAccessSec,
                               @Value("${jwt.expiration-refresh-sec}") final int expirationRefreshSec,
                               @Value("${jwt.secret:secret}") final String secret) {
        this.dateService = dateService;
        this.issuer = issuer;
        this.expirationAccessSec = expirationAccessSec;
        this.expirationRefreshSec = expirationRefreshSec;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    @Override
    public String getTokenFromRequest(@NonNull HttpServletRequest request) {
        String authHeader = request.getHeader(HEADER_NAME);

        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            return authHeader.substring(BEARER_PREFIX.length());
        } else {
            return null;
        }
    }

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, expirationAccessSec);
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails, expirationRefreshSec);
    }

    private String generateToken(UserDetails userDetails, int expiration) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof UserWithSecurity user) {
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
//            claims.put("role", user.getRole()); //Todo add role
        }
        return generateToken(claims, userDetails, expiration);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, int expiration) {
        final DateTime now = dateService.now();

        return Jwts.builder()
                .expiration(now.plusSeconds(expiration).toDate())
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(now.toDate())

                .signWith(secretKey)
                .issuer(issuer)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
