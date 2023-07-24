package com.jiinkim.todolist.user.service;


import com.jiinkim.todolist.user.service.dto.CheckDuplicateUsernameResponse;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;

public interface UserService {
    CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username);

    Object register(RegisterRequest toRegisterRequest);
}
