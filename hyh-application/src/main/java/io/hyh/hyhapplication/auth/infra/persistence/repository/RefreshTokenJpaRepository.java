package io.hyh.hyhapplication.auth.infra.persistence.repository;

import io.hyh.hyhapplication.auth.infra.persistence.entity.RefreshTokenJpaEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenJpaEntity, Long> {

    Optional<RefreshTokenJpaEntity> findByToken(String token);

    Optional<RefreshTokenJpaEntity> findByMemberIdAndRevokedIsFalse(@NotNull String memberId);

}
