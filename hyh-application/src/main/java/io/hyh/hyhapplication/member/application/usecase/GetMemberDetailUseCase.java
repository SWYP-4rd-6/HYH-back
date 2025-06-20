package io.hyh.hyhapplication.member.application.usecase;

import io.hyh.hyhapplication.member.application.usecase.dto.MemberDetail;
import io.hyh.hyhapplication.member.domain.MemberId;

public interface GetMemberDetailUseCase {

    MemberDetail getMemberDetail(MemberId memberId);

}
