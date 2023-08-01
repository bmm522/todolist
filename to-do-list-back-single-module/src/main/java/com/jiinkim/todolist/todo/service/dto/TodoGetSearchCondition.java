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
    private final String todoAt;

    public static TodoGetSearchCondition create(int page, Status isUpdate, Status isGetBeforeDataStatus, Optional<String> todoTitleOp, Optional<String> todoAtOp) {
        String todoTitle = todoTitleOp.orElse(null);
        String todoAt = todoAtOp.orElse(null);
        return new TodoGetSearchCondition(page, isUpdate, isGetBeforeDataStatus, todoTitle, todoAt);

    }

}
