package io.hyh.hyhapi.common.security;

import io.hyh.hyhapplication.auth.domain.AuthMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Stream;

public class AuthMemberDetails implements UserDetails {

    private final AuthMember authMember;


    public AuthMemberDetails(AuthMember authMember) {
        this.authMember = authMember;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of("ROLE_MEMBER")
                .map(role -> (GrantedAuthority) () -> role)
                .toList();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return authMember.getMemberId().getValue();
    }

}
