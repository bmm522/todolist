package com.jiinkim.todolist.todo.dao.model;


import com.jiinkim.todolist.common.config.mybatis.Status;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TodoModelDao {

        int insert(Todo todo);

        int updateTodoDone(@Param("todoId") Long todoId, @Param("todoDone") Status todoDone);

        int updateTodo(Todo todo);

        int batchDeleteTodoListByTodoId(
            @Param("deletedTodoIdList") List<Integer> deletedTodoIdList);

        int batchUpdateTodoDoneByTodoIdAndIsDone(
            @Param("updatedTodoIdList") List<Integer> updatedTodoIdList,
            @Param("done") Status isDone);

}
