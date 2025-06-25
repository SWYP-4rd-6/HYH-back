package io.hyh.hyhapplication.member.domain;

public interface MemberRepository {

    Member save(Member member);

    Member findById(MemberId id);

}
