package com.jiinkim.todolist.todo.dao.model;


import com.jiinkim.todolist.common.config.mybatis.Status;
import org.apache.ibatis.annotations.Param;

public interface TodoModelDao {

    int insert(Todo todo);

    int updateTodoDone(@Param("todoId") Long todoId,@Param("todoDone") Status todoDone);
}
