package com.example.hyh.auth.domain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TokenPort {

    @NotNull String generateAccessToken(@NotNull String memberId);

    @NotNull String generateRefreshToken(@NotNull String existingToken);

    boolean isValidToken(@Nullable String token);

    @NotNull String extractMemberId(@NotNull String token);

}
