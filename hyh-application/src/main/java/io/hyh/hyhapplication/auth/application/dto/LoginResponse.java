package io.hyh.hyhapplication.auth.application.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        String memberId,
        boolean isNewMember
) {
}
