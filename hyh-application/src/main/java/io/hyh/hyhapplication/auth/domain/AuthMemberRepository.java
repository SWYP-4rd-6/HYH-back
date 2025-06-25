package io.hyh.hyhapplication.auth.domain;

import java.util.Optional;

public interface AuthMemberRepository {

    AuthMember save(AuthMember authMember);

    Optional<AuthMember> findByMemberId(String memberId);

    Optional<AuthMember> findByProviderSuppliedIdAndAuthProvider(String providerSuppliedId, AuthProvider authProvider);

}
