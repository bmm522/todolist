package com.jiinkim.todolist.user.service;

import com.jiinkim.todolist.common.exception.NotFoundEntityException;
import com.jiinkim.todolist.user.dao.UserDao;
import com.jiinkim.todolist.user.model.User;
import com.jiinkim.todolist.user.service.dto.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService implements  UserDetailsService{


    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        User user = userDao.findUserByUsername(username)
                .orElseThrow(() -> new NotFoundEntityException("아이디에 해당하는 유저가 없습니다."));
        return new UserDetailsImpl(user);
    }
}
