package io.hyh.hyhapplication.member.application;

import io.hyh.hyhapplication.member.application.usecase.RegisterMemberUseCase;
import io.hyh.hyhapplication.member.application.usecase.dto.RegisterMemberCommand;
import io.hyh.hyhapplication.member.domain.Member;
import io.hyh.hyhapplication.member.domain.MemberId;
import io.hyh.hyhapplication.member.domain.MemberIdGenerator;
import io.hyh.hyhapplication.member.domain.MemberRepository;
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
                command.profileImageUrl()
        );
        return memberRepository.save(member);
    }

}
