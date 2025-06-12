package com.example.hyh.auth.representation.dto;

import com.example.hyh.member.domain.AuthProvider;

public record LoginRequest(String providerAccessToken, AuthProvider authProvider) {
}
