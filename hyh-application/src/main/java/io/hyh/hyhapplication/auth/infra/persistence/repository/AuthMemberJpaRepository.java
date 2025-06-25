package io.hyh.hyhapplication.auth.infra.persistence.repository;

import io.hyh.hyhapplication.auth.domain.AuthProvider;
import io.hyh.hyhapplication.auth.infra.persistence.entity.AuthMemberIdEmbeddable;
import io.hyh.hyhapplication.auth.infra.persistence.entity.AuthMemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthMemberJpaRepository extends JpaRepository<AuthMemberJpaEntity, AuthMemberIdEmbeddable> {

    Optional<AuthMemberJpaEntity> findByProviderSuppliedIdAndAuthProvider(String providerSuppliedId, AuthProvider authProvider);

    Optional<AuthMemberJpaEntity> findByMemberId(String memberId);

}
