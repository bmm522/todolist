package com.jiinkim.todolist.user.dao;


import com.jiinkim.todolist.user.dao.model.UserModelDao;
import com.jiinkim.todolist.user.dao.query.UserQueryDao;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao extends UserQueryDao, UserModelDao {



}
