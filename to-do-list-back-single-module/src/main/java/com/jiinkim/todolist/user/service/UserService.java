package com.jiinkim.todolist.user.service;


import com.jiinkim.todolist.user.service.dto.CheckDuplicateUsernameResponse;

public interface UserService {
    CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username);
}
