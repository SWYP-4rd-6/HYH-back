package com.example.hyh.auth.application.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        String memberId,
        boolean isNewMember
) {
}
