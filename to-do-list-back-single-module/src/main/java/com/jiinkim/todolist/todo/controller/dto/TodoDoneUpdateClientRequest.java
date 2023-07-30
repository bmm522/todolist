package com.jiinkim.todolist.todo.controller.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoDoneUpdateClientRequest {

  @NotNull
  private Long todoId;

  @NotNull
  private Status todoDone;
}
