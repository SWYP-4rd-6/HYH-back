package io.hyh.hyhapi.auth;

import io.hyh.hyhapi.auth.dto.LoginRequest;
import io.hyh.hyhapi.auth.dto.LogoutRequest;
import io.hyh.hyhapi.auth.dto.RefreshTokenRequest;
import io.hyh.hyhapplication.auth.application.LoginService;
import io.hyh.hyhapplication.auth.application.LogoutService;
import io.hyh.hyhapplication.auth.application.RefreshTokenService;
import io.hyh.hyhapplication.auth.application.dto.LoginCommand;
import io.hyh.hyhapplication.auth.application.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthController {

    private final LoginService loginService;
    private final RefreshTokenService refreshTokenService;
    private final LogoutService logoutService;


    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.login(new LoginCommand(
                request.providerAccessToken(),
                request.authProvider()
        ));
    }

    @PostMapping("/refresh")
    LoginResponse refreshToken(@RequestBody RefreshTokenRequest request) {
        return refreshTokenService.refreshToken(request.refreshToken());
    }

    @PostMapping("/logout")
    void logout(@RequestBody LogoutRequest request) {
        logoutService.logout(request.refreshToken());
    }

}
