package io.hyh.hyhapplication.auth.infra;

import io.hyh.hyhapplication.auth.domain.RefreshToken;
import io.hyh.hyhapplication.auth.domain.RefreshTokenRepository;
import io.hyh.hyhapplication.auth.infra.persistence.entity.RefreshTokenJpaEntity;
import io.hyh.hyhapplication.auth.infra.persistence.repository.RefreshTokenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;


    @Override
    public @NotNull RefreshToken save(@NotNull RefreshToken refreshToken) {
        return refreshTokenJpaRepository.save(RefreshTokenJpaEntity.from(refreshToken))
                .toDomain();
    }

    @Override
    public Optional<RefreshToken> findByToken(@NotNull String token) {
        return refreshTokenJpaRepository.findByToken(token)
                .map(RefreshTokenJpaEntity::toDomain);
    }

    @Override
    public Optional<RefreshToken> findByMemberId(@NotNull String memberId) {
        return refreshTokenJpaRepository.findByMemberIdAndRevokedIsFalse(memberId)
                .map(RefreshTokenJpaEntity::toDomain);
    }

}
