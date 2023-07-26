package com.jiinkim.todolist.user.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetNicknameResponse {

    private final String nickname;
    public static GetNicknameResponse create(final String nickname) {
        return new GetNicknameResponse(nickname);
    }
}
