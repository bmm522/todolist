package com.jiinkim.todolist.user.service;


import com.jiinkim.todolist.user.service.dto.CheckDuplicateUsernameResponse;
import com.jiinkim.todolist.user.service.dto.GetNicknameResponse;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;


public interface UserService {
    CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username);

    Integer register(RegisterRequest toRegisterRequest);

    GetNicknameResponse getNickname(Long userId);


}
