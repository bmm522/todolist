package com.jiinkim.todolist.todo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TodoBatchUpdateTodoDoneClientRequest {

    private List<Integer> updatedTodoIdList;
}
