package com.example.hyh.member.application.usecase.dto;

import com.example.hyh.member.domain.AuthProvider;

public record RegisterMemberCommand(
        String email,
        String nickname,
        String profileImageUrl,
        AuthProvider authProvider
) {
}
