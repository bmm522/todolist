package com.jiinkim.todolist.todo.dao;

import com.jiinkim.todolist.todo.dao.model.TodoModelDao;
import com.jiinkim.todolist.todo.dao.query.TodoQueryDao;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TodoDao extends TodoModelDao, TodoQueryDao {

}
