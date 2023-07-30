package com.jiinkim.todolist.todo.service.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoDoneUpdateRequest {

  private final Long userId;
  private final Long todoId;
  private final Status todoDone;

  public static TodoDoneUpdateRequest create(final Long userId, final Long todoId, final Status todoDone) {
    return new TodoDoneUpdateRequest(userId, todoId, todoDone);
  }


}
