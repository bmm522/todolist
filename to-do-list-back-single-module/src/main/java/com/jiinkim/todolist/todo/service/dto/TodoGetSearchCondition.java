package com.jiinkim.todolist.todo.service.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoGetSearchCondition {

    private final int page;
    private final Status isUpdate;
    private final Status isGetBeforeDataStatus;
    private final String todoTitle;
    private final String fromTodoAt;
    private final String toTodoAt;

    public static TodoGetSearchCondition create(int page, Status isUpdate, Status isGetBeforeDataStatus, Optional<String> todoTitleOp, Optional<String> fromTodoAtOp, Optional<String> toTodoAtOp) {
        String todoTitle = todoTitleOp.orElse(null);
        String fromTodoAt = fromTodoAtOp.orElse(null);
        String toTodoAt = toTodoAtOp.orElse(null);
        return new TodoGetSearchCondition(page, isUpdate, isGetBeforeDataStatus, todoTitle, fromTodoAt, toTodoAt);

    }

}
