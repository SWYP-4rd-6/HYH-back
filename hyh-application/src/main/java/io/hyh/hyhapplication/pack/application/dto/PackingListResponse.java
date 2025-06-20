package io.hyh.hyhapplication.pack.application.dto;

import io.hyh.hyhapplication.pack.domain.CheckList;

public record PackingListResponse(
        String userId,
        int check,
        String thing
) {
    public static PackingListResponse of(CheckList checkList) {
        return new PackingListResponse(
                checkList.getUser().getUserId(),
                checkList.getCheck(),
                checkList.getThing()
        );
    }
}
