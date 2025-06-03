package com.example.hyh.member.application.usecase.dto;

import com.example.hyh.member.domain.Member;

public record MemberDetail(
        String email,
        String nickname,
        String profileImageUrl
) {

    public MemberDetail(Member member) {
        this(member.getEmail(), member.getNickname(), member.getProfileImageUrl());
    }

}
