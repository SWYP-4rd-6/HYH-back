package io.hyh.hyhapplication.auth.application;

import io.hyh.hyhapplication.auth.application.dto.LoginCommand;
import io.hyh.hyhapplication.auth.application.dto.LoginResponse;
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
