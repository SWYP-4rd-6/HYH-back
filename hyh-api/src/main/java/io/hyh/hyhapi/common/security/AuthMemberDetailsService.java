package io.hyh.hyhapi.common.security;

import io.hyh.hyhapplication.auth.domain.AuthMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthMemberDetailsService implements UserDetailsService {

    private final AuthMemberRepository authMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return authMemberRepository.findByMemberId(memberId)
                .map(AuthMemberDetails::new)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Member not found with memberId: " + memberId));
    }

}
