package com.jiinkim.todolist.todo.service.dto.converter;

import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.todo.controller.dto.TodoBatchDeleteClientRequest;
import com.jiinkim.todolist.todo.controller.dto.TodoBatchUpdateTodoDoneClientRequest;
import com.jiinkim.todolist.todo.controller.dto.TodoInsertClientRequest;
import com.jiinkim.todolist.todo.controller.dto.TodoUpdateClientRequest;
import com.jiinkim.todolist.todo.service.dto.*;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class TodoServiceDtoConverter {

    public TodoInsertRequest of(final TodoInsertClientRequest request, final Long userId) {
        return TodoInsertRequest.create(request.getTodoTitle(), request.getTodoContent(), request.getTodoAt(), userId);
    }


    public TodoUpdateRequest of(final TodoUpdateClientRequest request, final Long userId) {
        return TodoUpdateRequest.create(request.getTodoId(), request.getTodoTitle(), request.getTodoContent(), request.getTodoAt(), request.getTodoDone(), userId);
    }


    public TodoBatchDeleteRequest of(final TodoBatchDeleteClientRequest request, final Long userId) {
        return TodoBatchDeleteRequest.create(request.getDeletedTodoIdList(), userId);
    }


    public TodoGetSearchCondition of(final int page, final Status isUpdate, final Status isGetBeforeDataStatus, Optional<String> todoTitle, final Optional<String> fromTodoAt, final Optional<String> toTodoAt) {
        return TodoGetSearchCondition.create(page, isUpdate, isGetBeforeDataStatus, todoTitle, fromTodoAt, toTodoAt);
    }

    public static TodoBatchUpdateTodoDoneRequest of(final TodoBatchUpdateTodoDoneClientRequest request, Status isDone, Long userId) {
        return TodoBatchUpdateTodoDoneRequest.create(request.getUpdatedTodoIdList(), isDone, userId);
    }
}
