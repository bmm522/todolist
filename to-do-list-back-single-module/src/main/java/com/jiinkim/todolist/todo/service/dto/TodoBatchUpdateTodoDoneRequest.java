package com.jiinkim.todolist.todo.service.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoBatchUpdateTodoDoneRequest {

    private final List<Integer> updatedTodoIdList;

    private final Status isDone;

    private final Long userId;

    public static TodoBatchUpdateTodoDoneRequest create(final List<Integer> updatedTodoIdList,
        final Status isDone, final Long userId) {

        return new TodoBatchUpdateTodoDoneRequest(updatedTodoIdList, isDone, userId);
    }


}
