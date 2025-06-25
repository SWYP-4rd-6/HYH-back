package io.hyh.hyhapi.member;

import io.hyh.hyhapplication.member.application.usecase.GetMemberDetailUseCase;
import io.hyh.hyhapplication.member.application.usecase.dto.MemberDetail;
import io.hyh.hyhapplication.member.domain.MemberId;
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
