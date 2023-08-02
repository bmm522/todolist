package com.jiinkim.todolist.todo.dao.query.dto.converter;

import com.jiinkim.todolist.todo.dao.query.dto.TodoListGetParams;
import com.jiinkim.todolist.todo.service.dto.TodoGetSearchCondition;
import lombok.experimental.UtilityClass;


@UtilityClass
public class TodoQueryDtoConverter {

        public TodoListGetParams of(final TodoGetSearchCondition condition, final Long userId) {

                return TodoListGetParams.create(condition.getPage(), userId,
                    condition.getIsUpdate(), condition.getIsGetBeforeDataStatus(),
                    condition.getTodoTitle(), condition.getFromTodoAt(), condition.getToTodoAt());
        }


}
