package io.hyh.hyhapplication.member.application.usecase.dto;

public record RegisterMemberCommand(
        String email,
        String nickname,
        String profileImageUrl
) {
}
