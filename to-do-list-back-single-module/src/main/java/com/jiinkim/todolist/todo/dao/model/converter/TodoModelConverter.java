package com.jiinkim.todolist.todo.dao.model.converter;

import com.jiinkim.todolist.todo.dao.model.Todo;
import com.jiinkim.todolist.todo.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.service.dto.TodoUpdateRequest;
import lombok.experimental.UtilityClass;


@UtilityClass
public class TodoModelConverter {
    
    public Todo from(TodoInsertRequest dto) {

        return Todo.createWhenInsert(dto.getTodoTitle(), dto.getTodoContent(),
            dto.getTodoAt(),
            dto.getUserId());
    }


    public Todo from(TodoUpdateRequest dto) {

        return Todo.createWhenUpdate(dto.getTodoId(), dto.getTodoTitle(),
            dto.getTodoContent(), dto.getTodoAt(), dto.getTodoDone(),
            dto.getUserId());
    }

}
