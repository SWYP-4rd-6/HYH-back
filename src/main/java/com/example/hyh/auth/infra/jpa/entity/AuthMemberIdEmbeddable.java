package com.example.hyh.auth.infra.jpa.entity;

import com.example.hyh.member.domain.MemberId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class AuthMemberIdEmbeddable {

    @Column(name = "id")
    private String value;

    public AuthMemberIdEmbeddable(String value) {
        this.value = value;
    }

    public static AuthMemberIdEmbeddable from(MemberId domainId) {
        return new AuthMemberIdEmbeddable(domainId.getValue());
    }

    public MemberId toDomainId() {
        return MemberId.of(this.value);
    }

}
