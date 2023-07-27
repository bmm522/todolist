package com.jiinkim.todolist.todo.controller.util;

import com.jiinkim.todolist.todo.application.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.controller.dto.TodoInsertClientRequest;
import com.jiinkim.todolist.user.jwt.JwtDecoder;

public class TodoClientDtoConverter {


    public static TodoInsertRequest toTodoInsertDto(final TodoInsertClientRequest request, Long userId) {
        return TodoInsertRequest.create(request.getTodoTitle(), request.getTodoContent(),  request.getTodoAt(), userId);
    }
}
