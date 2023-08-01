package com.jiinkim.todolist.todo.dao.query;

import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.todo.dao.query.dto.TodoListGetParams;
import com.jiinkim.todolist.todo.dao.query.dto.TodoQueryDto;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

public interface TodoQueryDao {

//    List<TodoQueryDto> findTodoListWithPaging(final Long offset, final Long userId);

    List<TodoQueryDto> findAllBySearchCondition(TodoListGetParams params);

    Optional<TodoQueryDto> findByTodoId(Long param1, Status param2);

    List<TodoQueryDto> findAllByTodoIdAndUserId(@Param("todoIdList") List<Integer> todoIdList);
}
