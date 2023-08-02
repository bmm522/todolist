package com.jiinkim.todolist.user.dao.query;

import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.user.dao.model.User;
import com.jiinkim.todolist.user.dao.query.dto.UserQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;


public interface UserQueryDao {

    int checkDuplicateByUsername(final String username);

    Optional<UserQueryDto> findUserByUsername(@Param("username") String username, @Param("status") Status status);

    String findNicknameByUserId(@Param("userId") Long userId, @Param("status") Status status);


    Optional<UserQueryDto> findUserByUserId(@Param("userId") Long userId, @Param("status") Status status);
}
