package com.example.hyh.member.application;

import com.example.hyh.member.application.usecase.GetMemberDetailUseCase;
import com.example.hyh.member.application.usecase.dto.MemberDetail;
import com.example.hyh.member.domain.MemberId;
import com.example.hyh.member.domain.MemberRepository;
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
