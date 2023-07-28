package com.jiinkim.todolist.todo.dao.model;


import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.common.utils.TimeUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Alias("todoModel")
public class Todo {

    private Long todoId;
    private String todoTitle;
    private String todoContent;
    private LocalDateTime todoAt;
    private Status todoDone;
    private Long userId;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private Todo(final String todoTitle, final String todoContent,LocalDateTime todoAt, final Long userId, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoAt = todoAt;
        this.userId= userId;
        this.todoDone = Status.N;
        this.status = Status.Y;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Todo createWhenTest(final Long todoId, final String todoTitle, final String todoContent,final LocalDateTime todoAt,final Status todoDone,  final Long userId, final Status status, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        return new Todo(todoId, todoTitle, todoContent, todoAt, todoDone, userId, status, createdAt, updatedAt);
    }

    public static Todo createWhenInsert(final String todoTitle,  final String todoContent, LocalDateTime todoAt, final Long userId) {
        LocalDateTime now = TimeUtils.getNow();
        return new Todo(todoTitle, todoContent, todoAt,userId,now, now);
    }


    public void setTodoAt(LocalDateTime todoAt) {
        this.todoAt = todoAt;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public void setTodoContent(String todoContent) {
        this.todoContent = todoContent;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }
}
