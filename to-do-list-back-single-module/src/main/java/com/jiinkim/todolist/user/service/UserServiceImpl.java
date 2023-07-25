package com.jiinkim.todolist.user.service;

import com.jiinkim.todolist.common.exception.InsertFailedException;
import com.jiinkim.todolist.common.exception.NotFoundEntityException;
import com.jiinkim.todolist.user.dao.UserDao;
import com.jiinkim.todolist.user.model.User;
import com.jiinkim.todolist.user.service.dto.CheckDuplicateUsernameResponse;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;
import com.jiinkim.todolist.user.service.dto.UserDetailsImpl;
import com.jiinkim.todolist.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    private final Encoder encoder;


    @Override
    public CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username) {
        int count = userDao.checkDuplicateByUsername(username);
        return CheckDuplicateUsernameResponse.createFromCount(count);
    }

    @Override
    public UserDto register(RegisterRequest dto)  {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String encodedPassword = encoder.encodeByBCryptPasswordEncoder(dto.getPassword());
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        User user = dto.toModel(getNow(), encodedPassword);
        if(userDao.register(user) != 1) {
             throw new InsertFailedException("Inserting user data failed");
        }
         return UserDto.create(user);
    }

    private LocalDateTime getNow() {
        LocalDateTime now = LocalDateTime.now();
        now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return now;
    }


}
