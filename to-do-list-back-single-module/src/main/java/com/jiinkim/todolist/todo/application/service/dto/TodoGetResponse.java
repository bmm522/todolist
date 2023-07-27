package com.jiinkim.todolist.todo.application.service.dto;

import com.jiinkim.todolist.todo.dao.query.dto.TodoQueryDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoGetResponse {

  private final List<TodoQueryDto> queryDtoList;

  private final Long page;

  public static TodoGetResponse create(final List<TodoQueryDto> queryDtoList, final Long page) {
    return new TodoGetResponse(queryDtoList, page);
  }
}
