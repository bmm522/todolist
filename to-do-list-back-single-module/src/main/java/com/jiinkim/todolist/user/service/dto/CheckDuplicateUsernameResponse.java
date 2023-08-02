package com.jiinkim.todolist.user.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckDuplicateUsernameResponse {

        private final boolean duplicateCheckResult;

        public static CheckDuplicateUsernameResponse create(final int count) {

                return new CheckDuplicateUsernameResponse(count == 1);
        }

}

