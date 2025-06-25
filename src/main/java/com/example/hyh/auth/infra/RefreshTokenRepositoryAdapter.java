package com.example.hyh.auth.infra;

import com.example.hyh.auth.domain.RefreshToken;
import com.example.hyh.auth.domain.RefreshTokenRepository;
import com.example.hyh.auth.infra.jpa.entity.RefreshTokenJpaEntity;
import com.example.hyh.auth.infra.jpa.repository.RefreshTokenJpaRepository;
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
