package io.hyh.hyhapplication.auth.application;

import io.hyh.hyhapplication.auth.application.dto.LoginCommand;
import io.hyh.hyhapplication.auth.application.dto.LoginResponse;
import io.hyh.hyhapplication.common.exception.ErrorCode;
import io.hyh.hyhapplication.common.exception.HyhApplicationException;
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
                .orElseThrow(() -> new HyhApplicationException(ErrorCode.INVALID_PROVIDER));

        return strategy.login(command);
    }

}
