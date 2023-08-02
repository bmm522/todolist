package com.jiinkim.todolist.common.config.security.filter.dto;

import com.jiinkim.todolist.common.jwt.JwtToken;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoWithJwtToken {

    private final JwtToken jwtToken;
    private final Long userId;

    private final String username;

    private final String nickname;

    public static UserInfoWithJwtToken create(final JwtToken jwtToken, final Long userId, final String username, final String nickname) {
        return new UserInfoWithJwtToken(jwtToken, userId, username, nickname);
    }
}
