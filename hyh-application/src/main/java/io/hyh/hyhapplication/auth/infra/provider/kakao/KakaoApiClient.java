package io.hyh.hyhapplication.auth.infra.provider.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.hyh.hyhapplication.common.exception.ErrorCode;
import io.hyh.hyhapplication.common.exception.HyhApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class KakaoApiClient {

    private final RestClient kakaoRestClient;


    public KakaoTokenInfoResponse getTokenInfo(String accessToken) {
        return kakaoRestClient.get()
                .uri("/v1/user/access_token_info")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new HyhApplicationException(ErrorCode.KAKAO_SERVER_AUTH_ERROR);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new HyhApplicationException(ErrorCode.KAKAO_SERVER_INTERNAL_ERROR);
                })
                .toEntity(KakaoTokenInfoResponse.class)
                .getBody();
    }

    public KakaoUserResponse getUserInfo(String accessToken) {
        return kakaoRestClient.post()
                .uri("/v2/user/me")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new HyhApplicationException(ErrorCode.KAKAO_SERVER_AUTH_ERROR);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new HyhApplicationException(ErrorCode.KAKAO_SERVER_INTERNAL_ERROR);
                })
                .toEntity(KakaoUserResponse.class)
                .getBody();
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record KakaoTokenInfoResponse(
//            long expiresInMillis,
            long id,
            long expiresIn,
            long appId
    ) {
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record KakaoUserResponse(
            long id,
            String connectedAt,
            Properties properties,
            KakaoAccount kakaoAccount
    ) {
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Properties(
                String nickname,
                String profileImage,
                String thumbnailImage
        ) {
        }

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record KakaoAccount(
                boolean profileNicknameNeedsAgreement,
                boolean profileImageNeedsAgreement,
                Profile profile
        ) {
            @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
            public record Profile(
                    String nickname,
                    String thumbnailImageUrl,
                    String profileImageUrl,
                    boolean isDefaultImage,
                    boolean isDefaultNickname
            ) {
            }
        }
    }
}
