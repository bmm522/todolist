package com.jiinkim.todolist.user.service;

import com.jiinkim.todolist.common.exception.InsertFailedException;
import com.jiinkim.todolist.common.exception.NotFoundEntityException;
import com.jiinkim.todolist.user.dao.UserDao;
import com.jiinkim.todolist.user.jwt.JwtMaker;
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
public class UserServiceImpl implements UserService,  UserDetailsService{

    private final UserDao userDao;

    private final Encoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        System.out.println(username);
        User user = userDao.findUserByUsername(username)
            .orElseThrow(() -> new NotFoundEntityException("아이디에 해당하는 유저가 없습니다."));
        return new UserDetailsImpl(user);
    }

    @Override
    public CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username) {
        int count = userDao.checkDuplicateByUsername(username);
        return CheckDuplicateUsernameResponse.createFromCount(count);
    }

    @Override
    public UserDto register(RegisterRequest dto)  {
        String encodedPassword = encoder.encodeByBCryptPasswordEncoder(dto.getPassword());
        String refreshToken = JwtMaker.makeRefreshToken();
        User user = dto.toModel(getNow(), encodedPassword, refreshToken);
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
