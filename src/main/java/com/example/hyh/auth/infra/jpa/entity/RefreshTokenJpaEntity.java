package com.example.hyh.auth.infra.jpa.entity;

import com.example.hyh.auth.domain.RefreshToken;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RefreshTokenJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @Column(name = "revoked", nullable = false)
    private boolean revoked = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;


    public RefreshTokenJpaEntity(Long id, String token, String memberId, boolean revoked, LocalDateTime createdAt) {
        this.id = id;
        this.token = token;
        this.memberId = memberId;
        this.revoked = revoked;
        this.createdAt = createdAt;
    }

    public static RefreshTokenJpaEntity from(Long id, String token, String memberId, boolean revoked, LocalDateTime createdAt) {
        return new RefreshTokenJpaEntity(id, token, memberId, revoked, createdAt);
    }

    public static RefreshTokenJpaEntity from(RefreshToken refreshToken) {
        return new RefreshTokenJpaEntity(refreshToken.getId(), refreshToken.getToken(), refreshToken.getMemberId(), refreshToken.isRevoked(), refreshToken.getCreatedAt());
    }

    public RefreshToken toDomain() {
        return new RefreshToken(id, token, memberId, revoked, createdAt);
    }

}

