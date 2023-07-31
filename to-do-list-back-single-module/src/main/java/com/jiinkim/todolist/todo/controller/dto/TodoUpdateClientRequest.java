package com.jiinkim.todolist.todo.controller.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
public class TodoUpdateClientRequest {

    @NotNull
    private Long todoId;

    @NotNull
    private String todoTitle;

    private String todoContent;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime todoAt;

    @NotNull
    private Status todoDone;
}
