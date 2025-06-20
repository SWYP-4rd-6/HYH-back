package io.hyh.hyhapplication.member.infra.persistence;

import io.hyh.hyhapplication.member.domain.MemberId;
import io.hyh.hyhapplication.member.domain.MemberIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberIdGeneratorImpl implements MemberIdGenerator {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public MemberId generate() {
        Long sequence = memberJpaRepository.getAuthMemberSequence();
        return MemberId.fromSequence(sequence);
    }

}
