package com.example.hyh.member.application.usecase;

import com.example.hyh.member.application.usecase.dto.MemberDetail;
import com.example.hyh.member.domain.MemberId;

public interface GetMemberDetailUseCase {

    MemberDetail getMemberDetail(MemberId memberId);

}
