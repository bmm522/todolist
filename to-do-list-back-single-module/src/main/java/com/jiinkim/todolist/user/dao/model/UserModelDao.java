package com.jiinkim.todolist.user.dao.model;

import org.apache.ibatis.annotations.Mapper;


public interface UserModelDao {

    int updateRefreshToken(User savedUser);

    int register(User user);
}
