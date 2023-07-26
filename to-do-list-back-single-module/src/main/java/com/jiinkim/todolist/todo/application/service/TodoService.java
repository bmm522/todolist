package com.jiinkim.todolist.todo.application.service;

import com.jiinkim.todolist.common.exception.InsertFailedException;
import com.jiinkim.todolist.todo.application.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.dao.TodoDao;
import com.jiinkim.todolist.todo.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoDao todoDao;

    public Integer insert(TodoInsertRequest dto) {
        Todo todo =  dto.toModel();
        int result = todoDao.insert(todo);
        if(result != 1) {
            throw new InsertFailedException("Inserting Todo data failed");
        }
        return result;
    }
}
