package com.jiinkim.todolist.todo.service.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoListDeleteRequest {

  private final List<Integer> deletedTodoIdList;

  private final Long userId;

  public static TodoListDeleteRequest create(final List<Integer> deletedTodoIdList, final Long userId) {
    return new TodoListDeleteRequest(deletedTodoIdList, userId);
  }
}
