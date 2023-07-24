package com.jiinkim.todolist.user.controller;

import com.jiinkim.todolist.user.controller.dto.ApiResponse;
import com.jiinkim.todolist.user.controller.dto.RegisterRequest;
import com.jiinkim.todolist.user.service.UserService;
import com.jiinkim.todolist.user.service.dto.CheckDuplicateUsernameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    /**
     *
     * @param  username 중복 체크 하고자 하는 유저 아이디
     * @return isDuplicated : true 중복값 있음, false 중복값 없음
     */
    @GetMapping("/check-duplicate")
    public ApiResponse<CheckDuplicateUsernameResponse> checkDuplicatedUsername(@RequestParam("username")String username) {
        return ApiResponse.success(200, userService.checkDuplicatedUserId(username));
    }

    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody RegisterRequest request) {
        System.out.println(request.getUsername());
        System.out.println(request.getPassword());
        return null;
    }
}
