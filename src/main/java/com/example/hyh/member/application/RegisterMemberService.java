package com.example.hyh.member.application;

import com.example.hyh.member.application.usecase.RegisterMemberUseCase;
import com.example.hyh.member.application.usecase.dto.RegisterMemberCommand;
import com.example.hyh.member.domain.Member;
import com.example.hyh.member.domain.MemberId;
import com.example.hyh.member.domain.MemberIdGenerator;
import com.example.hyh.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RegisterMemberService implements RegisterMemberUseCase {

    private final MemberRepository memberRepository;
    private final MemberIdGenerator memberIdGenerator;

    @Override
    public Member registerMember(RegisterMemberCommand command) {
        MemberId id = memberIdGenerator.generate();
        Member member = new Member(
                id,
                command.email(),
                command.nickname(),
                command.profileImageUrl(),
                command.authProvider()
        );
        return memberRepository.save(member);
    }

}
