package com.example.hyh.auth.representation;

import com.example.hyh.auth.application.LoginService;
import com.example.hyh.auth.application.dto.LoginCommand;
import com.example.hyh.auth.application.dto.LoginResponse;
import com.example.hyh.member.domain.AuthProvider;
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


    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.login(new LoginCommand(
                request.providerAccessToken(),
                request.authProvider()
        ));
    }


    public record LoginRequest(String providerAccessToken, AuthProvider authProvider) {
    }


}
