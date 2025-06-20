package io.hyh.hyhapplication.auth.application;

import io.hyh.hyhapplication.auth.application.dto.LoginResponse;
import io.hyh.hyhapplication.auth.domain.RefreshToken;
import io.hyh.hyhapplication.auth.domain.RefreshTokenRepository;
import io.hyh.hyhapplication.auth.domain.TokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {

    private final TokenPort tokenService;
    private final RefreshTokenRepository refreshTokenRepository;


    public LoginResponse generateToken(String accessToken, boolean isNewMember) {
        String memberId = tokenService.extractMemberId(accessToken);

        // 기존 토큰 무효화
        refreshTokenRepository.findByMemberId(memberId)
                .ifPresent(token -> {
                    token.revoke();
                    refreshTokenRepository.save(token);
                });

        String refreshToken = tokenService.generateRefreshToken(accessToken);
        refreshTokenRepository.save(new RefreshToken(refreshToken, memberId));

        return new LoginResponse(accessToken, refreshToken, memberId, isNewMember);
    }

    public LoginResponse refreshToken(String refreshToken) {
        if (!tokenService.isValidToken(refreshToken)) {
            throw new IllegalArgumentException("invalid refresh token[38]");
        }

        refreshTokenRepository.findByToken(refreshToken)
                .ifPresentOrElse(
                        token -> {
                            if (token.isRevoked()) {
                                throw new IllegalArgumentException("invalid refresh token[45]");
                            }
                            token.revoke();
                            refreshTokenRepository.save(token);
                        },
                        () -> {
                            throw new IllegalArgumentException("invalid refresh token[51]");
                        }
                );

        String memberId = tokenService.extractMemberId(refreshToken);
        String newAccessToken = tokenService.generateAccessToken(memberId);
        String newRefreshToken = tokenService.generateRefreshToken(newAccessToken);

        refreshTokenRepository.save(new RefreshToken(newRefreshToken, memberId));

        return new LoginResponse(newAccessToken, newRefreshToken, memberId, false);
    }

    public void invalidateToken(String refreshToken) {
        refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("invalid refresh token"));

        String memberId = tokenService.extractMemberId(refreshToken);

        // 기존 토큰 무효화
        refreshTokenRepository.findByMemberId(memberId)
                .ifPresent(token -> {
                    token.revoke();
                    refreshTokenRepository.save(token);
                });
    }

}
