package com.jiinkim.todolist.user.dao.model;

import com.jiinkim.todolist.user.model.User;

public interface UserModelDao {

    int updateRefreshToken(User savedUser);

    int register(User user);
}
