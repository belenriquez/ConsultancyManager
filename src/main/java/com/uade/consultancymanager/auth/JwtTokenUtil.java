package com.uade.consultancymanager.auth;

/**
import com.uade.screenspace.entity.RefreshToken;
import com.uade.screenspace.entity.User;
import com.uade.screenspace.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

    @Value("${app.jwt.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    public static String IS_OWNER_CLAIM = "IS_OWNER_CLAIM";
    public static String EMAIL_CLAIM = "EMAIL_CLAIM";

    public String generateAccessToken(User user, boolean isOwner) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(IS_OWNER_CLAIM, isOwner);
        claims.put(EMAIL_CLAIM, user.getEmail());

        return Jwts.builder()
                .setIssuer("ScreenSpace")
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setSubject(String.format("%s,%s,%s", user.getId(), user.getEmail(), isOwner))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpirationDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());

        return refreshToken;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace");
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }

        return refreshTokenRepository.findByJwt(token).isPresent();
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}

 **/