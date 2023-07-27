package com.jiinkim.todolist.todo.dao.query;

import com.jiinkim.todolist.todo.dao.query.dto.TodoGetRequest;

public class TodoQueryDtoConverter {

  public static TodoGetRequest of(final Long page, final Long userId) {
    return TodoGetRequest.create(page, userId);
  }
}
