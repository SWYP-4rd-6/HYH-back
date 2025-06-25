package io.hyh.hyhapi.auth.dto;

public record LogoutRequest(
        String refreshToken
) {
}
