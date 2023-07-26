package com.jiinkim.todolist.user.dao.query;

import com.jiinkim.todolist.user.model.User;

import java.util.Optional;

public interface UserQueryDao {

    int checkDuplicateByUsername(final String username) ;

    Optional<User> findUserByUsername(String username);

    String findNicknameByUserId(Long userId);
}
