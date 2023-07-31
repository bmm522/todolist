package com.jiinkim.todolist.todo.controller.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;

import java.time.LocalDateTime;

public class TodoUpdateClientRequest {
    private Long todoId;
    private String todoTitle;
    private String todoContent;
    private LocalDateTime todoAt;
    private Status todoDone;
}
