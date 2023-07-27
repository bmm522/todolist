package com.jiinkim.todolist.todo.controller.util;

import com.jiinkim.todolist.todo.dao.query.dto.TodoGetRequest;
import com.jiinkim.todolist.todo.application.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.controller.dto.TodoInsertClientRequest;

public class TodoClientDtoConverter {


    public static TodoInsertRequest of(final TodoInsertClientRequest request,final Long userId) {
        return TodoInsertRequest.create(request.getTodoTitle(), request.getTodoContent(),  request.getTodoAt(), userId);
    }


    public static TodoGetRequest of(final Long page,final Long userId) {
        return TodoGetRequest.create(page, userId);
    }
}
