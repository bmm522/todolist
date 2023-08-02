package com.jiinkim.todolist.user.controller;

import com.jiinkim.todolist.common.config.security.CurrentUserId;
import com.jiinkim.todolist.common.dto.ApiResponse;
import com.jiinkim.todolist.user.controller.dto.RegisterClientRequest;
import com.jiinkim.todolist.user.controller.dto.ReissueTokenRequest;
import com.jiinkim.todolist.user.controller.dto.UpdateNicknameClientRequest;
import com.jiinkim.todolist.user.service.UserService;

import com.jiinkim.todolist.user.service.dto.*;
import com.jiinkim.todolist.user.service.dto.converter.UserServiceDtoConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * @param username 중복 체크 하고자 하는 유저 아이디
     * @return isDuplicated : true 중복값 있음, false 중복값 없음
     */
    @GetMapping("/check-duplicate")
    public ApiResponse<CheckDuplicateUsernameResponse> checkDuplicatedUsername(
            @RequestParam("username") String username) {
        return ApiResponse.success(HttpStatus.OK, userService.checkDuplicatedUserId(username));
    }

    @PostMapping("/register")
    public ApiResponse<Integer> register(
            @RequestBody @Valid RegisterClientRequest request) {
        RegisterRequest dto = UserServiceDtoConverter.from(request);
        return ApiResponse.success(HttpStatus.CREATED, userService.register(dto));
    }

    @PostMapping("/re-issue")
    public ApiResponse<ReissueTokenResponse> reIssueToken(@RequestBody @Valid ReissueTokenRequest request) {
        return ApiResponse.success(HttpStatus.OK, userService.reIssueToken(request));
    }


    @GetMapping("/nickname")
    public ApiResponse<GetNicknameResponse> getNickname(
            @CurrentUserId Long userId) {
        return ApiResponse.success(HttpStatus.OK, userService.getNickname(userId));
    }

    @PostMapping("/nickname/update")
    public ApiResponse<UpdateNicknameResponse> updateNickname(@RequestBody @Valid UpdateNicknameClientRequest request, @CurrentUserId Long userId) {
        UpdateNicknameRequest dto = UserServiceDtoConverter.of(request, userId);
        return ApiResponse.success(HttpStatus.OK, userService.updateNickname(dto));
    }

}