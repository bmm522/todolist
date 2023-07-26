package com.jiinkim.todolist.todo.application.service.dto;

import com.jiinkim.todolist.todo.model.Todo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoInsertRequest {

    private final String todoTitle;

    private final String todoContent;



    private final LocalDateTime todoAt;

    private final Long userId;




    public static TodoInsertRequest create(final String todoTitle, final String todoContent, final LocalDateTime todoAt, final Long userId) {
        return new TodoInsertRequest(todoTitle, todoContent,  todoAt, userId);
    }

    public Todo toModel() {
        return Todo.createWhenInsert(todoTitle, todoContent, todoAt, userId);

    }
}
