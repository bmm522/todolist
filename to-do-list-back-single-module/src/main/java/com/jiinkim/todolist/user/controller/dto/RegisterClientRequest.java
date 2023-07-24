package com.jiinkim.todolist.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterClientRequest {

    private String username;
    private String password;

}