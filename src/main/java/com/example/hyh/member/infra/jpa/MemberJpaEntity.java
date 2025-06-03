package com.example.hyh.member.infra.jpa;

import com.example.hyh.member.domain.AuthProvider;
import com.example.hyh.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberJpaEntity {

    @EmbeddedId
    private MemberIdEmbeddable id;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider", nullable = false)
    private AuthProvider authProvider;

    public static MemberJpaEntity from(Member member) {
        MemberJpaEntity entity = new MemberJpaEntity();
        entity.id = MemberIdEmbeddable.from(member.getId());
        entity.email = member.getEmail();
        entity.nickname = member.getNickname();
        entity.profileImageUrl = member.getProfileImageUrl();
        entity.authProvider = member.getAuthProvider();
        return entity;
    }

    public Member toDomainModel() {
        return new Member(
                this.id.toDomainId(),
                this.email,
                this.nickname,
                this.profileImageUrl,
                this.authProvider
        );
    }

}
