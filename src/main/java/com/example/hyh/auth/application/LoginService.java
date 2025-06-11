package com.example.hyh.auth.application;

import com.example.hyh.auth.application.dto.LoginCommand;
import com.example.hyh.auth.application.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final List<LoginStrategy> loginStrategies;


    public LoginResponse login(LoginCommand command) {
        LoginStrategy strategy = loginStrategies.stream()
                .filter(s -> s.supports(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported provider"));

        return strategy.login(command);
    }

}
