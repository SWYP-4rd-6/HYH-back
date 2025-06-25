package io.hyh.hyhapplication.member.application;

import io.hyh.hyhapplication.member.application.usecase.GetMemberDetailUseCase;
import io.hyh.hyhapplication.member.application.usecase.dto.MemberDetail;
import io.hyh.hyhapplication.member.domain.MemberId;
import io.hyh.hyhapplication.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
class GetMemberService implements GetMemberDetailUseCase {

    private final MemberRepository memberRepository;


    @Override
    public MemberDetail getMemberDetail(MemberId memberId) {
        return new MemberDetail(memberRepository.findById(memberId));
    }

}
