package com.jiinkim.todolist.todo.model;


import com.jiinkim.todolist.common.utils.TimeUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Todo {

    private Long todoId;
    private String todoTitle;
    private String todoContent;
    private LocalDateTime todoAt;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    private Todo(final String todoTitle, final String todoContent,  LocalDateTime todoAt, final Long userId,  final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.userId= userId;
        this.todoAt = todoAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public static Todo createWhenInsert(final String todoTitle, final String todoContent, LocalDateTime todoAt, final Long userId) {
        LocalDateTime now = TimeUtil.getNow();
        return new Todo(todoTitle, todoContent,  todoAt,userId,now, now);
    }

}
