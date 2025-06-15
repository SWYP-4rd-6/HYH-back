package com.example.hyh.pack.application.dto;

import com.example.hyh.pack.domain.CheckList;

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
