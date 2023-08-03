package com.jiinkim.todolist.todo.service.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoUpdateRequest {

    private final Long todoId;

    private final String todoTitle;

    private final String todoContent;

    private final LocalDateTime todoAt;

    private final Status todoDone;

    private final Long userId;

    public static TodoUpdateRequest create(final Long todoId, final String todoTitle,
        final String todoContent,
        final LocalDateTime todoAt,
        final Status todoDone, final Long userId) {

        return new TodoUpdateRequest(todoId, todoTitle, todoContent, todoAt, todoDone,
            userId);
    }


}
