package io.hyh.hyhapplication.auth.application;

import io.hyh.hyhapplication.auth.domain.AuthMember;
import io.hyh.hyhapplication.auth.domain.AuthMemberRepository;
import io.hyh.hyhapplication.auth.domain.AuthProvider;
import io.hyh.hyhapplication.member.domain.MemberId;
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
