package com.example.hyh.member.representation;

import com.example.hyh.member.application.usecase.GetMemberDetailUseCase;
import com.example.hyh.member.application.usecase.dto.MemberDetail;
import com.example.hyh.member.domain.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
class MemberRestController {

    private final GetMemberDetailUseCase getMemberDetailUseCase;


    @GetMapping("/{memberId}")
    MemberDetail getMemberDetail(@PathVariable String memberId) {
        return getMemberDetailUseCase.getMemberDetail(MemberId.of(memberId));
    }

}
