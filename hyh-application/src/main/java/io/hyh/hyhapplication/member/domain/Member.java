package io.hyh.hyhapplication.member.domain;

import lombok.Getter;

@Getter
public class Member {

    private final MemberId id;

    private String email;

    private String nickname;

    private String profileImageUrl;

    public Member(MemberId id, String email, String nickname, String profileImageUrl) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

}
