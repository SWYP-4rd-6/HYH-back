package io.hyh.hyhapplication.auth.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RefreshToken {

    private final Long id;
    private final String token;
    private final String memberId;
    private boolean revoked;
    private final LocalDateTime createdAt;

    public RefreshToken(String token, String memberId) {
        this.id = null;
        this.token = token;
        this.memberId = memberId;
        this.createdAt = LocalDateTime.now();
        this.revoked = false;
    }

    public RefreshToken(Long id, String token, String memberId, boolean revoked, LocalDateTime createdAt) {
        this.id = id;
        this.token = token;
        this.memberId = memberId;
        this.revoked = revoked;
        this.createdAt = createdAt;
    }

    public void revoke() {
        this.revoked = true;
    }

}

