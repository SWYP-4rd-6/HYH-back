package com.example.hyh.auth.infra.provider.kakao;

import com.example.hyh.auth.application.AuthMemberService;
import com.example.hyh.auth.application.LoginStrategy;
import com.example.hyh.auth.application.TokenService;
import com.example.hyh.auth.application.dto.LoginCommand;
import com.example.hyh.auth.application.dto.LoginResponse;
import com.example.hyh.auth.domain.AuthMember;
import com.example.hyh.member.application.usecase.RegisterMemberUseCase;
import com.example.hyh.member.application.usecase.dto.RegisterMemberCommand;
import com.example.hyh.member.domain.AuthProvider;
import com.example.hyh.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoLoginStrategy implements LoginStrategy {

    private final TokenService tokenService;
    private final KakaoApiClient kakaoApiClient;
    private final AuthMemberService authMemberService;
    private final RegisterMemberUseCase registerMemberUseCase;


    @Override
    public LoginResponse login(LoginCommand command) {
        var tokenInfo = kakaoApiClient.getTokenInfo(command.providerAccessToken());
        if (tokenInfo.expiresIn() <= 0) {
            throw new IllegalArgumentException("access token is expired");
        }

        KakaoApiClient.KakaoUserResponse userResponse = kakaoApiClient.getUserInfo(command.providerAccessToken());
        Long kakaoUserId = userResponse.id();

        return authMemberService.findByProviderSuppliedId(String.valueOf(kakaoUserId), AuthProvider.Kakao)
                .map(authMember -> {
                    String memberId = authMember.getMemberId().getValue();
                    String accessToken = tokenService.generateAccessToken(memberId);
                    String refreshToken = tokenService.generateRefreshToken(accessToken);
                    return new LoginResponse(
                            accessToken,
                            refreshToken,
                            memberId,
                            false
                    );
                })
                .orElseGet(() -> {
                    Member member = registerMemberUseCase.registerMember(new RegisterMemberCommand(
                            null,
                            userResponse.kakaoAccount().profile().nickname(),
                            userResponse.kakaoAccount().profile().profileImageUrl(),
                            AuthProvider.Kakao
                    ));
                    AuthMember newAuthMember = authMemberService.createAuthMember(
                            member.getId(),
                            String.valueOf(kakaoUserId),
                            AuthProvider.Kakao
                    );
                    String memberId = newAuthMember.getMemberId().getValue();
                    String accessToken = tokenService.generateAccessToken(memberId);
                    String refreshToken = tokenService.generateRefreshToken(accessToken);
                    return new LoginResponse(
                            accessToken,
                            refreshToken,
                            memberId,
                            true
                    );
                });
    }

    @Override
    public boolean supports(LoginCommand command) {
        return command.authProvider() == AuthProvider.Kakao && command.providerAccessToken() != null;
    }

}
