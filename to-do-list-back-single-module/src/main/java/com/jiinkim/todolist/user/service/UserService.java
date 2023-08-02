package com.jiinkim.todolist.user.service;


import com.jiinkim.todolist.user.controller.dto.ReissueTokenRequest;
import com.jiinkim.todolist.user.service.dto.*;


public interface UserService {
    CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username);

    Integer register(final RegisterRequest toRegisterRequest);

    GetNicknameResponse getNickname(final Long userId);


    ReissueTokenResponse reIssueToken(final ReissueTokenRequest request);

    UpdateNicknameResponse updateNickname(UpdateNicknameRequest dto);
}
