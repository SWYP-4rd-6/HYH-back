package com.example.hyh.auth.representation;

import com.example.hyh.auth.application.LoginService;
import com.example.hyh.auth.application.LogoutService;
import com.example.hyh.auth.application.RefreshTokenService;
import com.example.hyh.auth.application.dto.LoginCommand;
import com.example.hyh.auth.application.dto.LoginResponse;
import com.example.hyh.auth.representation.dto.LoginRequest;
import com.example.hyh.auth.representation.dto.LogoutRequest;
import com.example.hyh.auth.representation.dto.RefreshTokenRequest;
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
