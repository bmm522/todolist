package com.jiinkim.todolist.todo.dao.query;

import com.jiinkim.todolist.todo.dao.query.dto.TodoListGetParams;
import com.jiinkim.todolist.todo.dao.query.dto.TodoQueryDto;

import java.util.List;

public interface TodoQueryDao {

//    List<TodoQueryDto> findTodoListWithPaging(final Long offset, final Long userId);

    List<TodoQueryDto> findAllByUserIdWithPaging(TodoListGetParams params);
}
