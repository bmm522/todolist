package com.jiinkim.todolist.user.dao.query;

import com.jiinkim.todolist.user.dao.model.User;
import com.jiinkim.todolist.user.dao.query.dto.UserQueryDto;

import java.util.Optional;


public interface UserQueryDao {

    int checkDuplicateByUsername(final String username) ;

    Optional<UserQueryDto> findUserByUsername(String username);

    String findNicknameByUserId(Long userId);


}
