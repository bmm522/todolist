package com.jiinkim.todolist.todo.dao.query.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoQueryDto {

    private Long todoId;
    private String todoTitle;
    private String todoContent;
    private boolean todoDone;
    private LocalDateTime todoAt;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
