package io.hyh.hyhapplication.auth.infra.persistence.entity;

import io.hyh.hyhapplication.auth.domain.AuthMember;
import io.hyh.hyhapplication.auth.domain.AuthProvider;
import io.hyh.hyhapplication.member.domain.MemberId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthMemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @Column(name = "provider_supplied_id", nullable = false)
    private String providerSuppliedId;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider", nullable = false)
    private AuthProvider authProvider;


    public static AuthMemberJpaEntity from(AuthMember authMember) {
        AuthMemberJpaEntity entity = new AuthMemberJpaEntity();
        entity.id = authMember.getId();
        entity.memberId = authMember.getMemberId().getValue();
        entity.providerSuppliedId = authMember.getProviderSuppliedId();
        entity.authProvider = authMember.getAuthProvider();
        return entity;
    }

    public AuthMember toDomainModel() {
        return new AuthMember(
                this.id,
                MemberId.of(this.memberId),
                this.providerSuppliedId,
                this.authProvider
        );
    }

}
