package io.hyh.hyhapplication.auth.infra.persistence;

import io.hyh.hyhapplication.auth.domain.AuthMember;
import io.hyh.hyhapplication.auth.domain.AuthMemberRepository;
import io.hyh.hyhapplication.auth.domain.AuthProvider;
import io.hyh.hyhapplication.auth.infra.persistence.entity.AuthMemberJpaEntity;
import io.hyh.hyhapplication.auth.infra.persistence.repository.AuthMemberJpaRepository;
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
