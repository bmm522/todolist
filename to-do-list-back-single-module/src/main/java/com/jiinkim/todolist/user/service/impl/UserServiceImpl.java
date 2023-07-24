package com.jiinkim.todolist.user.service.impl;

import com.jiinkim.todolist.user.dao.UserDao;
import com.jiinkim.todolist.user.service.UserService;
import com.jiinkim.todolist.user.service.dto.CheckDuplicateUsernameResponse;
import com.jiinkim.todolist.user.service.util.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username) {
        int count = userDao.checkDuplicateByUsername(username);
        return CheckDuplicateUsernameResponse.createFromCount(count);
    }
}
