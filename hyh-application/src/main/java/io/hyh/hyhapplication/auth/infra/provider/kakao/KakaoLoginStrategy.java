package io.hyh.hyhapplication.auth.infra.provider.kakao;

import io.hyh.hyhapplication.auth.application.AuthMemberService;
import io.hyh.hyhapplication.auth.application.LoginStrategy;
import io.hyh.hyhapplication.auth.application.RefreshTokenService;
import io.hyh.hyhapplication.auth.application.TokenService;
import io.hyh.hyhapplication.auth.application.dto.LoginCommand;
import io.hyh.hyhapplication.auth.application.dto.LoginResponse;
import io.hyh.hyhapplication.auth.domain.AuthMember;
import io.hyh.hyhapplication.auth.domain.AuthProvider;
import io.hyh.hyhapplication.common.exception.ErrorCode;
import io.hyh.hyhapplication.common.exception.HyhApplicationException;
import io.hyh.hyhapplication.member.application.usecase.RegisterMemberUseCase;
import io.hyh.hyhapplication.member.application.usecase.dto.RegisterMemberCommand;
import io.hyh.hyhapplication.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoLoginStrategy implements LoginStrategy {

    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;
    private final KakaoApiClient kakaoApiClient;
    private final AuthMemberService authMemberService;
    private final RegisterMemberUseCase registerMemberUseCase;


    @Override
    public LoginResponse login(LoginCommand command) {
        var tokenInfo = kakaoApiClient.getTokenInfo(command.providerAccessToken());
        if (tokenInfo.expiresIn() <= 0) {
            throw new HyhApplicationException(ErrorCode.ACCESS_TOKEN_EXPIRED);
        }

        KakaoApiClient.KakaoUserResponse userResponse = kakaoApiClient.getUserInfo(command.providerAccessToken());
        Long kakaoUserId = userResponse.id();

        return authMemberService.findByProviderSuppliedId(String.valueOf(kakaoUserId), AuthProvider.Kakao)
                .map(authMember -> {
                    String memberId = authMember.getMemberId().getValue();
                    String accessToken = tokenService.generateAccessToken(memberId);
                    return refreshTokenService.generateToken(accessToken, false);
                })
                .orElseGet(() -> {
                    Member member = registerMemberUseCase.registerMember(new RegisterMemberCommand(
                            null,
                            userResponse.kakaoAccount().profile().nickname(),
                            userResponse.kakaoAccount().profile().profileImageUrl()
                    ));
                    AuthMember newAuthMember = authMemberService.createAuthMember(
                            member.getId(),
                            String.valueOf(kakaoUserId),
                            AuthProvider.Kakao
                    );
                    String memberId = newAuthMember.getMemberId().getValue();
                    String accessToken = tokenService.generateAccessToken(memberId);
                    return refreshTokenService.generateToken(accessToken, true);
                });
    }

    @Override
    public boolean supports(LoginCommand command) {
        return command.authProvider() == AuthProvider.Kakao && command.providerAccessToken() != null;
    }

}
