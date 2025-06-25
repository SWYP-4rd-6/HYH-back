package io.hyh.hyhapi.auth.dto;

import io.hyh.hyhapplication.auth.domain.AuthProvider;

public record LoginRequest(String providerAccessToken, AuthProvider authProvider) {
}
