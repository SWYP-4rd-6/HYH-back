package io.hyh.hyhapplication.auth.domain;

import io.hyh.hyhapplication.member.domain.MemberId;
import lombok.Getter;

@Getter
public class AuthMember {

    private final Long id;

    private final MemberId memberId;

    private final String providerSuppliedId;

    private final AuthProvider authProvider;


    public AuthMember(Long id, MemberId memberId, String providerSuppliedId, AuthProvider authProvider) {
        this.id = id;
        this.memberId = memberId;
        this.providerSuppliedId = providerSuppliedId;
        this.authProvider = authProvider;
    }

}
