package com.example.hyh.global.config.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // TODO 에러 클래스 정의
        response.setContentType("application/json;charset=UTF-8");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "invalid token");
    }

}
