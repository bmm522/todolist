package com.jiinkim.todolist.todo.controller.dto;

import com.jiinkim.todolist.todo.dao.query.dto.TodoGetRequest;
import com.jiinkim.todolist.todo.application.service.dto.TodoInsertRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TodoServiceDtoConverter {


    public TodoInsertRequest of(final TodoInsertClientRequest request,final Long userId) {
        return TodoInsertRequest.create(request.getTodoTitle(), request.getTodoContent(),  request.getTodoAt(), userId);
    }


    public TodoGetRequest of(final Long page,final Long userId) {
        return TodoGetRequest.create(page, userId);
    }
}
