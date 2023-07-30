package com.jiinkim.todolist.todo.dao.query.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TodoQueryDtoConverter {

    public TodoListGetParams of(final int page, final Long userId, final Status isUpdate) {
        return TodoListGetParams.create(page, userId, isUpdate);
    }


}
