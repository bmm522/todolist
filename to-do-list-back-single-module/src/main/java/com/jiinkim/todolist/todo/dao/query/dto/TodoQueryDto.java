package com.jiinkim.todolist.todo.dao.query.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Alias("todoQueryDto")
public class TodoQueryDto {

    private Long todoId;
    private String todoTitle;
    private String todoContent;
    private Status todoDone;
    private LocalDateTime todoAt;
    private Long userId;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public boolean isPermission(final Long userId) {
        return this.userId.equals(userId);
    }

}
