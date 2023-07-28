package com.jiinkim.todolist.todo.service.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.todo.dao.query.dto.TodoQueryDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoGetResponse {

  Map<LocalDateTime, List<TodoQueryDto>> timeBucketTodoMap;


  public static TodoGetResponse create(Map<LocalDateTime, List<TodoQueryDto>> timeBucketTodoMap) {
    return new TodoGetResponse(timeBucketTodoMap);
  }

}
