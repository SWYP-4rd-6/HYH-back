package com.example.hyh.member.infra;

import com.example.hyh.member.domain.Member;
import com.example.hyh.member.domain.MemberId;
import com.example.hyh.member.domain.MemberRepository;
import com.example.hyh.member.infra.jpa.MemberIdEmbeddable;
import com.example.hyh.member.infra.jpa.MemberJpaEntity;
import com.example.hyh.member.infra.jpa.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryAdapter implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;


    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(MemberJpaEntity.from(member))
                .toDomainModel();
    }

    @Override
    public Member findById(MemberId id) {
        return memberJpaRepository.findById(MemberIdEmbeddable.from(id))
                .map(MemberJpaEntity::toDomainModel)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));
    }

}
