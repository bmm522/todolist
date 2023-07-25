package com.jiinkim.todolist.user.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckDuplicateUsernameResponse {

    private final boolean duplicateCheckResult;

    public static CheckDuplicateUsernameResponse create(final boolean isDuplicated) {
        return new CheckDuplicateUsernameResponse(isDuplicated);
    }

    public static CheckDuplicateUsernameResponse createFromCount(final int count) {
        if (count == 1) {
            return CheckDuplicateUsernameResponse.create(true);
        }
        return CheckDuplicateUsernameResponse.create(false);
    }
}

