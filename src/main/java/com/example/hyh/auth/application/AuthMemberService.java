package com.example.hyh.auth.application;

import com.example.hyh.auth.domain.AuthMember;
import com.example.hyh.auth.domain.AuthMemberRepository;
import com.example.hyh.member.domain.AuthProvider;
import com.example.hyh.member.domain.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthMemberService {

    private final AuthMemberRepository authMemberRepository;


    public AuthMember createAuthMember(MemberId memberId, String providerSuppliedId, AuthProvider authProvider) {
        AuthMember authMember = new AuthMember(
                null,
                memberId,
                providerSuppliedId,
                authProvider
        );
        return authMemberRepository.save(authMember);
    }

    public Optional<AuthMember> findByProviderSuppliedId(String providerSuppliedId, AuthProvider authProvider) {
        return authMemberRepository.findByProviderSuppliedIdAndAuthProvider(providerSuppliedId, authProvider);
    }

}
