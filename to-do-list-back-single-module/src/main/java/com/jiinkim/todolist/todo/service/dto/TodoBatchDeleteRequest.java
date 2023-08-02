package com.jiinkim.todolist.todo.service.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoBatchDeleteRequest {

        private final List<Integer> deletedTodoIdList;


        private final Long userId;

        public static TodoBatchDeleteRequest create(final List<Integer> deletedTodoIdList,
            final Long userId) {

                return new TodoBatchDeleteRequest(deletedTodoIdList, userId);
        }

}
