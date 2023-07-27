package com.jiinkim.todolist.todo.dao.model;

import com.jiinkim.todolist.todo.dao.query.dto.TodoQueryDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TodoConverter {

    public Todo to(TodoQueryDto dto) {
        return Todo.create(dto.getTodoId(), dto.getTodoTitle(), dto.getTodoContent(), dto.getTodoAt(), dto.isTodoDone(), dto.getUserId(), dto.getCreatedAt(),dto.getUpdatedAt());
    }
}
