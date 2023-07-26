package com.jiinkim.todolist.todo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TodoInsertClientRequest {

    private String todoTitle;
    private String todoContent;
    private LocalDateTime todoAt;

}
