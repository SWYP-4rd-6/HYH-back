package com.example.hyh.member.domain;

import lombok.Getter;

@Getter
public class Member {

    private final MemberId id;

    private String email;

    private String nickname;

    private String profileImageUrl;

    private final AuthProvider authProvider;

    public Member(MemberId id, String email, String nickname, String profileImageUrl, AuthProvider authProvider) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.authProvider = authProvider;
    }

}
