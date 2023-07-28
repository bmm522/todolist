package com.jiinkim.todolist.todo.dao.query.dto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TodoQueryDtoConverter {

    public TodoListGetParams of(final int page, final Long userId) {
        return TodoListGetParams.create(page, userId);
    }


}
