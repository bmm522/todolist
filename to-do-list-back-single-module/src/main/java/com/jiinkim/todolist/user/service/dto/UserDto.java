package com.jiinkim.todolist.user.service.dto;

import com.jiinkim.todolist.user.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserDto {

    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserDto create(final User user) {
        return new UserDto(user.getUsername(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
