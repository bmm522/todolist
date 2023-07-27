package com.jiinkim.todolist.user.service;

import com.jiinkim.todolist.common.exception.InsertFailedException;
import com.jiinkim.todolist.common.exception.NotFoundQueryResultException;
import com.jiinkim.todolist.user.dao.UserDao;
import com.jiinkim.todolist.user.dao.model.UserModelConverter;
import com.jiinkim.todolist.user.dao.query.dto.UserQueryDto;
import com.jiinkim.todolist.user.jwt.JwtMaker;
import com.jiinkim.todolist.user.dao.model.User;
import com.jiinkim.todolist.user.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService,  UserDetailsService{

    private final UserDao userDao;

    private final Encoder encoder;
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserQueryDto userQueryDto = userDao.findUserByUsername(username)
            .orElseThrow(() -> new NotFoundQueryResultException("아이디에 해당하는 유저가 없습니다."));
        return new UserDetailsImpl(UserModelConverter.from(userQueryDto));
    }

    @Override
    public CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username) {
        int count = userDao.checkDuplicateByUsername(username);
        return CheckDuplicateUsernameResponse.create(count);
    }

    @Override
    public Integer register(RegisterRequest dto)  {
        String encodedPassword = encoder.encodeByBCryptPasswordEncoder(dto.getPassword());
        String refreshToken = JwtMaker.makeRefreshToken(dto.getUsername());
        User user = dto.toModel(encodedPassword, refreshToken);
        int result = userDao.register(user);
        if(result != 1) {
             throw new InsertFailedException("Inserting user data failed");
        }
         return result;
    }

    @Override
    public GetNicknameResponse getNickname(final Long userId) {
        String nickname =userDao.findNicknameByUserId(userId);
        if(!StringUtils.hasText(nickname)) {
            throw new NotFoundQueryResultException("유저 PK값에 해당 하는 닉네임이 없습니다.");
        }
        return GetNicknameResponse.create(nickname);
    }

//    @Override
//    public String findNicknameByUserId(Long userId) {
//        return userDao.findNicknameByUserId(userId);
//    }

}
