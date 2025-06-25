package io.hyh.hyhapplication.member.application.usecase;

import io.hyh.hyhapplication.member.application.usecase.dto.RegisterMemberCommand;
import io.hyh.hyhapplication.member.domain.Member;

public interface RegisterMemberUseCase {

    Member registerMember(RegisterMemberCommand command);

}
