package com.example.hyh.auth.application;

import com.example.hyh.auth.application.dto.LoginCommand;
import com.example.hyh.auth.application.dto.LoginResponse;

public interface LoginStrategy {

    boolean supports(LoginCommand command);

    LoginResponse login(LoginCommand command);

}
