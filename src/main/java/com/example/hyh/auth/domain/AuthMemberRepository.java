package com.example.hyh.auth.domain;

import com.example.hyh.member.domain.AuthProvider;

import java.util.Optional;

public interface AuthMemberRepository {

    AuthMember save(AuthMember authMember);

    Optional<AuthMember> findByMemberId(String memberId);

    Optional<AuthMember> findByProviderSuppliedIdAndAuthProvider(String providerSuppliedId, AuthProvider authProvider);

}
