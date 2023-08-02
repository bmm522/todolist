package com.jiinkim.todolist.todo.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoInsertRequest {

        private final String todoTitle;

        private final String todoContent;

        private final LocalDateTime todoAt;

        private final Long userId;



        public static TodoInsertRequest create(final String todoTitle, final String todoContent,
            final LocalDateTime todoAt, final Long userId) {

                return new TodoInsertRequest(todoTitle, todoContent, todoAt, userId);
        }

}
