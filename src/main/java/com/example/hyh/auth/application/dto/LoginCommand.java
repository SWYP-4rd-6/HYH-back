package com.example.hyh.auth.application.dto;

import com.example.hyh.member.domain.AuthProvider;

public record LoginCommand(
        String providerAccessToken,
        AuthProvider authProvider
) {
}
