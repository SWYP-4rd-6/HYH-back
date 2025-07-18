package io.hyh.hyhapplication.auth.application;

import io.hyh.hyhapplication.auth.domain.TokenPort;
import io.hyh.hyhapplication.common.exception.ErrorCode;
import io.hyh.hyhapplication.common.exception.HyhApplicationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenService implements TokenPort {

    private final SecretKey secretKey;
    private final long accessTokenValidTime;
    private final long refreshTokenValidTime;

    public TokenService(@Value("${jwt.secret-key}") String secretKey,
                        @Value("${jwt.access-token-validity-seconds:3600000}") long accessTokenValidTime,
                        @Value("${jwt.refresh-token-validity-seconds:604800000}") long refreshTokenValidTime) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.accessTokenValidTime = accessTokenValidTime;
        this.refreshTokenValidTime = refreshTokenValidTime;
    }

    @Override
    public @NotNull String generateAccessToken(@NotNull String memberId) {
        Claims claims = Jwts.claims()
                .subject(memberId)
                .add("memberId", memberId)
                .build();
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + accessTokenValidTime);
        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(expiredAt)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public @NotNull String generateRefreshToken(@NotNull String token) {
        Claims claims = parseJwtClaims(token);
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + refreshTokenValidTime);
        return Jwts.builder()
                .subject(claims.getSubject())
                .claims(claims)
                .issuedAt(now)
                .expiration(expiredAt)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public boolean isValidToken(@Nullable String token) {
        try {
            parseJwtClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public @NotNull String extractMemberId(@NotNull String token) {
        return parseJwtClaims(token)
                .get("memberId", String.class);
    }

    private Claims parseJwtClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException |
                 MalformedJwtException |
                 SignatureException e) {
            throw new HyhApplicationException(ErrorCode.INVALID_TOKEN);
        } catch (Exception e) {
            // fixme 별도 예외?
            throw new HyhApplicationException(ErrorCode.INVALID_TOKEN);
        }
    }

}
