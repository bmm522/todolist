package com.jiinkim.todolist.user.dao;


import com.jiinkim.todolist.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserDao {

    int checkDuplicateByUsername(final String username) ;

}
