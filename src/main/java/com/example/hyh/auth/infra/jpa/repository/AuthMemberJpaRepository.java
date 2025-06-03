package com.example.hyh.auth.infra.jpa.repository;

import com.example.hyh.auth.infra.jpa.entity.AuthMemberIdEmbeddable;
import com.example.hyh.auth.infra.jpa.entity.AuthMemberJpaEntity;
import com.example.hyh.member.domain.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthMemberJpaRepository extends JpaRepository<AuthMemberJpaEntity, AuthMemberIdEmbeddable> {

    Optional<AuthMemberJpaEntity> findByProviderSuppliedIdAndAuthProvider(String providerSuppliedId, AuthProvider authProvider);

    Optional<AuthMemberJpaEntity> findByMemberId(String memberId);
}
