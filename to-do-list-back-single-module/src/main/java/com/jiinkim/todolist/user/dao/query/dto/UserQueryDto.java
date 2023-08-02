package com.jiinkim.todolist.user.dao.query.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserQueryDto {

    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private String refreshToken;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public boolean isPermission(final Long userId) {
        return this.userId.equals(userId);
    }

}
