package com.jiinkim.todolist.todo.controller.dto;

import com.jiinkim.todolist.todo.service.dto.TodoDoneUpdateRequest;
import com.jiinkim.todolist.todo.service.dto.TodoInsertRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TodoServiceDtoConverter {


    public TodoInsertRequest of(final TodoInsertClientRequest request,final Long userId) {
        return TodoInsertRequest.create(request.getTodoTitle(), request.getTodoContent(),  request.getTodoAt(), userId);
    }

    public TodoDoneUpdateRequest of(final TodoDoneUpdateClientRequest request, final Long userId) {
        return TodoDoneUpdateRequest.create(userId, request.getTodoId(), request.getTodoDone());
    }



}
