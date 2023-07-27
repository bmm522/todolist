package com.jiinkim.todolist.user.dao.model;

import com.jiinkim.todolist.user.dao.query.dto.UserQueryDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

    public User from(UserQueryDto dto) {
        return User.create(dto.getUserId(), dto.getUsername(), dto.getPassword(), dto.getNickname(), dto.getRefreshToken(), dto.getCreatedAt(), dto.getUpdatedAt());
    }
}
