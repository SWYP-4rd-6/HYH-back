package io.hyh.hyhapplication.auth.application.dto;

import io.hyh.hyhapplication.auth.domain.AuthProvider;

public record LoginCommand(
        String providerAccessToken,
        AuthProvider authProvider
) {
}
