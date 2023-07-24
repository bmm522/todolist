package com.jiinkim.todolist.user.service.dto;

import lombok.Getter;

@Getter
public class CheckDuplicateUsernameResponse {

    private final boolean duplicateCheckResult;

    private CheckDuplicateUsernameResponse(final boolean duplicateCheckResult) {
        this.duplicateCheckResult = duplicateCheckResult;
    }

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

