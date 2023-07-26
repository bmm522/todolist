package com.jiinkim.todolist.user.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@AllArgsConstructor
public class RegisterClientRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;

}
