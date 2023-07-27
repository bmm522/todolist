package com.jiinkim.todolist.todo.dao.query;

import com.jiinkim.todolist.todo.dao.query.dto.TodoQueryDto;

import java.util.List;
import java.util.Optional;

public interface TodoQueryDao {

    List<TodoQueryDto> findTodoListWithPaging(final Long offset, final Long userId);

}
