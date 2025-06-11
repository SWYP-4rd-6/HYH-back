package com.example.hyh.auth.application;

import com.example.hyh.auth.application.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenService {

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

    public String generateAccessToken(String memberId) {
        Claims claims = Jwts.claims()
                .subject(memberId)
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

    public String generateRefreshToken(String token) {
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

    public Claims validateToken(String token) {
        try {
            return parseJwtClaims(token);
        } catch (Exception e) {
            // TODO 상세 예외 처리
            throw new InvalidTokenException();
        }
    }

    private Claims parseJwtClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getMemberId(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
