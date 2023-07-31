package com.jiinkim.todolist.todo.controller.dto;

import com.jiinkim.todolist.todo.service.dto.TodoDoneUpdateRequest;
import com.jiinkim.todolist.todo.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.service.dto.TodoListDeleteRequest;
import com.jiinkim.todolist.todo.service.dto.TodoUpdateRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TodoServiceDtoConverter {


    public TodoInsertRequest of(final TodoInsertClientRequest request,final Long userId) {
        return TodoInsertRequest.create(request.getTodoTitle(), request.getTodoContent(),  request.getTodoAt(), userId);
    }

    public TodoDoneUpdateRequest of(final TodoDoneUpdateClientRequest request, final Long userId) {
        return TodoDoneUpdateRequest.create(userId, request.getTodoId(), request.getTodoDone());
    }

    public TodoUpdateRequest of(final TodoUpdateClientRequest request, final Long userId) {
        return TodoUpdateRequest.create(request.getTodoId(), request.getTodoTitle(), request.getTodoContent(), request.getTodoAt(), request.getTodoDone(), userId);
    }

    public TodoListDeleteRequest of(final TodoListDeleteClientRequest request, final Long userId) {
        return TodoListDeleteRequest.create(request.getDeletedTodoIdList(), userId);
    }



}
