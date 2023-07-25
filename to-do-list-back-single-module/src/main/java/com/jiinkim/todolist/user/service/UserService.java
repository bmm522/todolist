package com.jiinkim.todolist.user.service;


import com.jiinkim.todolist.common.exception.InsertFailedException;
import com.jiinkim.todolist.user.service.dto.CheckDuplicateUsernameResponse;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;
import com.jiinkim.todolist.user.service.dto.UserDto;

public interface UserService {
    CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username);

    UserDto register(RegisterRequest toRegisterRequest) throws InsertFailedException;
}
