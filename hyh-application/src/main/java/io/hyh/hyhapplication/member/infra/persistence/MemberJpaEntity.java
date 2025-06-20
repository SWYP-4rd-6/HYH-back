package io.hyh.hyhapplication.member.infra.persistence;

import io.hyh.hyhapplication.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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


    public static MemberJpaEntity from(Member member) {
        MemberJpaEntity entity = new MemberJpaEntity();
        entity.id = MemberIdEmbeddable.from(member.getId());
        entity.email = member.getEmail();
        entity.nickname = member.getNickname();
        entity.profileImageUrl = member.getProfileImageUrl();
        return entity;
    }

    public Member toDomainModel() {
        return new Member(
                this.id.toDomainId(),
                this.email,
                this.nickname,
                this.profileImageUrl
        );
    }

}
