package com.jiinkim.todolist.user.service;


import com.jiinkim.todolist.common.exception.InsertFailedException;
import com.jiinkim.todolist.user.jwt.JwtToken;
import com.jiinkim.todolist.user.service.dto.CheckDuplicateUsernameResponse;
import com.jiinkim.todolist.user.service.dto.GetNicknameResponse;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;
import com.jiinkim.todolist.user.service.dto.UserDto;

public interface UserService {
    CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username);

    Integer register(RegisterRequest toRegisterRequest) throws InsertFailedException;

    GetNicknameResponse getNickname(JwtToken jwtToken);

    String findNicknameByUserId(Long userId);
}
