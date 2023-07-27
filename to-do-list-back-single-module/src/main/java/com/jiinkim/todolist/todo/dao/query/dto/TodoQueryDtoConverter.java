package com.jiinkim.todolist.todo.dao.query.dto;

import com.jiinkim.todolist.todo.dao.query.dto.TodoGetRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TodoQueryDtoConverter {

  public TodoGetRequest of(final Long page, final Long userId) {
    return TodoGetRequest.create(page, userId);
  }
}
