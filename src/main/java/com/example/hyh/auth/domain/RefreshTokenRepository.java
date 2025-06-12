package com.example.hyh.auth.domain;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface RefreshTokenRepository {

    @NotNull RefreshToken save(@NotNull RefreshToken refreshToken);

    Optional<RefreshToken> findByToken(@NotNull String token);

    Optional<RefreshToken> findByMemberId(@NotNull String memberId);

}
