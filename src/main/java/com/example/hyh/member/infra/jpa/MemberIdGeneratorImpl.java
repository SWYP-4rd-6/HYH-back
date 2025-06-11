package com.example.hyh.member.infra.jpa;

import com.example.hyh.member.domain.MemberId;
import com.example.hyh.member.domain.MemberIdGenerator;
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
