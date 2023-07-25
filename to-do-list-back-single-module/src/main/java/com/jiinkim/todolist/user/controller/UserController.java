package com.jiinkim.todolist.user.controller;

import com.jiinkim.todolist.common.exception.InsertFailedException;
import com.jiinkim.todolist.user.controller.dto.ApiResponse;
import com.jiinkim.todolist.user.controller.dto.RegisterClientRequest;
import com.jiinkim.todolist.user.controller.util.DtoConverter;
import com.jiinkim.todolist.user.service.UserService;

import com.jiinkim.todolist.user.service.dto.RegisterRequest;
import com.jiinkim.todolist.user.service.dto.UserDetailsImpl;
import com.jiinkim.todolist.user.service.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
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
    public ApiResponse<?> checkDuplicatedUsername(
        @RequestParam("username") String username) {
        return ApiResponse.success(HttpStatus.OK, userService.checkDuplicatedUserId(username));
    }

    @PostMapping("/register")
    public ApiResponse<UserDto> register(@RequestBody @Valid RegisterClientRequest request)  {
        RegisterRequest dto = DtoConverter.toRegisterRequest(request);
        return ApiResponse.success(HttpStatus.CREATED, userService.register(dto));
    }

//    @GetMapping("/username")
//    public ApiResponse<String> getUsername(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        System.out.println(userDetails.getUsername());
//        return ApiResponse.success(HttpStatus.OK, userDetails.getUsername());
//    }
}