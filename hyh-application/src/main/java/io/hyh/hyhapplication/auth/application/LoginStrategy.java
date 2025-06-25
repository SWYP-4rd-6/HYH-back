package io.hyh.hyhapplication.auth.application;

import io.hyh.hyhapplication.auth.application.dto.LoginCommand;
import io.hyh.hyhapplication.auth.application.dto.LoginResponse;

public interface LoginStrategy {

    boolean supports(LoginCommand command);

    LoginResponse login(LoginCommand command);

}
