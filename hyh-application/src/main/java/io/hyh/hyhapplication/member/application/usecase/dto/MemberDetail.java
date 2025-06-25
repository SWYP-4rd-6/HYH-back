package io.hyh.hyhapplication.member.application.usecase.dto;

import io.hyh.hyhapplication.member.domain.Member;

public record MemberDetail(
        String email,
        String nickname,
        String profileImageUrl
) {

    public MemberDetail(Member member) {
        this(member.getEmail(), member.getNickname(), member.getProfileImageUrl());
    }

}
