package com.jiinkim.todolist.todo.application.service;

import com.jiinkim.todolist.common.exception.InsertFailedException;
import com.jiinkim.todolist.todo.application.service.dto.TodoGetResponse;
import com.jiinkim.todolist.todo.application.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.dao.TodoDao;
import com.jiinkim.todolist.todo.dao.model.Todo;
import com.jiinkim.todolist.todo.dao.model.TodoConverter;
import com.jiinkim.todolist.todo.dao.query.TodoQueryDtoConverter;
import com.jiinkim.todolist.todo.dao.query.dto.TodoGetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoDao todoDao;

    public Integer insert(final TodoInsertRequest dto) {
        Todo todo = TodoConverter.from(dto);
        int result = todoDao.insert(todo);
        if(result != 1) {
            throw new InsertFailedException("Inserting Todo data failed");
        }
        return result;
    }

  public TodoGetResponse getTodoList(final Long page, final Long userId) {
    TodoGetRequest dto = TodoQueryDtoConverter.of(page, userId);
    return null;
  }
}

