package com.example.hyh.member.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberJpaRepository extends JpaRepository<MemberJpaEntity, MemberIdEmbeddable> {

    @Query(value = "SELECT nextval('member_id_seq')", nativeQuery = true)
    Long getAuthMemberSequence();

}
