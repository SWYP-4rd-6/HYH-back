package io.hyh.hyhapplication.member.infra;

import io.hyh.hyhapplication.member.domain.Member;
import io.hyh.hyhapplication.member.domain.MemberId;
import io.hyh.hyhapplication.member.domain.MemberRepository;
import io.hyh.hyhapplication.member.infra.persistence.MemberIdEmbeddable;
import io.hyh.hyhapplication.member.infra.persistence.MemberJpaEntity;
import io.hyh.hyhapplication.member.infra.persistence.MemberJpaRepository;
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
