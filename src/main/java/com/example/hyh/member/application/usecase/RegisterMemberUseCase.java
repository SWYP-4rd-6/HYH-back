package com.example.hyh.member.application.usecase;

import com.example.hyh.member.application.usecase.dto.RegisterMemberCommand;
import com.example.hyh.member.domain.Member;

public interface RegisterMemberUseCase {

    Member registerMember(RegisterMemberCommand command);

}
