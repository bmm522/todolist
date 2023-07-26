package com.jiinkim.todolist.todo.dao.model;

import com.jiinkim.todolist.todo.application.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.model.Todo;
import org.apache.ibatis.annotations.Mapper;


public interface TodoModelDao {

    int insert(Todo todo);
}
