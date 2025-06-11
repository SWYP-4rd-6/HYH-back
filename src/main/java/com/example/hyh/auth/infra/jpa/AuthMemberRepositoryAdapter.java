package com.example.hyh.auth.infra.jpa;

import com.example.hyh.auth.domain.AuthMember;
import com.example.hyh.auth.domain.AuthMemberRepository;
import com.example.hyh.auth.infra.jpa.entity.AuthMemberJpaEntity;
import com.example.hyh.auth.infra.jpa.repository.AuthMemberJpaRepository;
import com.example.hyh.member.domain.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthMemberRepositoryAdapter implements AuthMemberRepository {

    private final AuthMemberJpaRepository authMemberJpaRepository;


    @Override
    public AuthMember save(AuthMember authMember) {
        return authMemberJpaRepository.save(AuthMemberJpaEntity.from(authMember))
                .toDomainModel();
    }

    @Override
    public Optional<AuthMember> findByMemberId(String memberId) {
        return authMemberJpaRepository.findByMemberId(memberId)
                .map(AuthMemberJpaEntity::toDomainModel);
    }

    @Override
    public Optional<AuthMember> findByProviderSuppliedIdAndAuthProvider(String providerSuppliedId, AuthProvider authProvider) {
        return authMemberJpaRepository.findByProviderSuppliedIdAndAuthProvider(providerSuppliedId, authProvider)
                .map(AuthMemberJpaEntity::toDomainModel);
    }

}
