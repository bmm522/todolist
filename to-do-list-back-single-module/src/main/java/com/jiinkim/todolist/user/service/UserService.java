package com.jiinkim.todolist.user.service;


import com.jiinkim.todolist.user.controller.dto.ReissueTokenRequest;
import com.jiinkim.todolist.user.service.dto.CheckDuplicateUsernameResponse;
import com.jiinkim.todolist.user.service.dto.GetNicknameResponse;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;
import com.jiinkim.todolist.user.service.dto.ReissueTokenResponse;


public interface UserService {
    CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username);

    Integer register(final RegisterRequest toRegisterRequest);

    GetNicknameResponse getNickname(final Long userId);


    ReissueTokenResponse reIssueToken(final ReissueTokenRequest request);
}
