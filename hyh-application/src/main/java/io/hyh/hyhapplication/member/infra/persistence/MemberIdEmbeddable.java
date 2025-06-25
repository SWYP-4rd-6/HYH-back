package io.hyh.hyhapplication.member.infra.persistence;

import io.hyh.hyhapplication.member.domain.MemberId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class MemberIdEmbeddable {

    @Column(name = "id")
    private String value;

    public MemberIdEmbeddable(String value) {
        this.value = value;
    }

    public static MemberIdEmbeddable from(MemberId domainId) {
        return new MemberIdEmbeddable(domainId.getValue());
    }

    public MemberId toDomainId() {
        return MemberId.of(this.value);
    }

}
