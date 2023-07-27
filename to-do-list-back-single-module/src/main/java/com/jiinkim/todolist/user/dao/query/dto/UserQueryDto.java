package com.jiinkim.todolist.user.dao.query.dto;

import com.jiinkim.todolist.user.dao.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserQueryDto {

    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private String refreshToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}