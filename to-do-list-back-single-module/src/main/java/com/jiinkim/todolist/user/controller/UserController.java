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
         * 유저 아이디의 중복 여부를 체크합니다.
         *
         * @param username 중복 체크 하고자 하는 유저 아이디
         * @return ApiResponse<CheckDuplicateUsernameResponse> 중복 여부를 담은 ApiResponse 객체
         */
        @GetMapping("/check-duplicate")
        public ApiResponse<CheckDuplicateUsernameResponse> checkDuplicatedUsername(
            @RequestParam("username") String username) {

                return ApiResponse.success(HttpStatus.OK,
                    userService.checkDuplicatedUserId(username));
        }

        /**
         * 클라이언트가 회원 가입을 요청합니다.
         *
         * @param request 회원 가입 요청 데이터를 담은 RegisterClientRequest 객체
         * @return ApiResponse<Integer> 회원 가입 결과를 담은 ApiResponse 객체
         */
        @PostMapping("/register")
        public ApiResponse<Integer> register(
            @RequestBody @Valid RegisterClientRequest request) {

                RegisterRequest dto = UserServiceDtoConverter.from(request);
                return ApiResponse.success(HttpStatus.CREATED, userService.register(dto));
        }

        /**
         * 액세스 토큰 재발급을 요청합니다.
         *
         * @param request 토큰 재발급 요청 데이터를 담은 ReissueTokenRequest 객체
         * @return ApiResponse<ReissueTokenResponse> 재발급된 토큰을 담은 ApiResponse 객체
         */
        @PostMapping("/re-issue")
        public ApiResponse<ReissueTokenResponse> reIssueToken(
            @RequestBody @Valid ReissueTokenRequest request) {

                return ApiResponse.success(HttpStatus.OK, userService.reIssueToken(request));
        }


        /**
         * 현재 사용자의 닉네임을 조회합니다.
         *
         * @param userId 현재 사용자의 아이디
         * @return ApiResponse<GetNicknameResponse> 닉네임 정보를 담은 ApiResponse 객체
         */
        @GetMapping("/nickname")
        public ApiResponse<GetNicknameResponse> getNickname(
            @CurrentUserId Long userId) {

                return ApiResponse.success(HttpStatus.OK, userService.getNickname(userId));
        }

        /**
         * 현재 사용자의 닉네임을 업데이트합니다.
         *
         * @param request 업데이트할 닉네임 정보를 담은 UpdateNicknameClientRequest 객체
         * @param userId  현재 사용자의 아이디
         * @return ApiResponse<UpdateNicknameResponse> 업데이트된 닉네임 정보를 담은 ApiResponse 객체
         */
        @PostMapping("/nickname/update")
        public ApiResponse<UpdateNicknameResponse> updateNickname(
            @RequestBody @Valid UpdateNicknameClientRequest request, @CurrentUserId Long userId) {

                UpdateNicknameRequest dto = UserServiceDtoConverter.of(request, userId);
                return ApiResponse.success(HttpStatus.OK, userService.updateNickname(dto));
        }

}