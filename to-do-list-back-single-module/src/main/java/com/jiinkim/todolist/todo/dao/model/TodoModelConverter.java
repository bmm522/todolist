package com.jiinkim.todolist.todo.dao.model;

import com.jiinkim.todolist.todo.service.dto.TodoInsertRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TodoModelConverter {

//    public Todo from(TodoQueryDto dto) {
//        return Todo.create(dto.getTodoId(), dto.getTodoTitle(), dto.getTodoContent(), dto.getTodoAt(), dto.isTodoDone(), dto.getUserId(), dto.getCreatedAt(),dto.getUpdatedAt());
//    }

    public Todo from(TodoInsertRequest dto) {
        return Todo.createWhenInsert(dto.getTodoTitle(), dto.getTodoContent(), dto.getTodoAt(),
            dto.getUserId());
    }
}
