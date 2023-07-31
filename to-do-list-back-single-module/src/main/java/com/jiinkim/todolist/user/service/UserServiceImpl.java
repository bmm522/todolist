package com.jiinkim.todolist.user.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.common.exception.InsertFailedException;
import com.jiinkim.todolist.common.exception.NotFoundQueryResultException;
import com.jiinkim.todolist.common.exception.UserInsertFailedException;
import com.jiinkim.todolist.common.exception.UserNotFoundQueryResultException;
import com.jiinkim.todolist.user.dao.UserDao;
import com.jiinkim.todolist.user.dao.model.UserModelConverter;
import com.jiinkim.todolist.user.dao.query.dto.UserQueryDto;
import com.jiinkim.todolist.user.jwt.JwtGenerator;
import com.jiinkim.todolist.user.dao.model.User;
import com.jiinkim.todolist.user.jwt.JwtProvider;
import com.jiinkim.todolist.user.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    private final Encoder encoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserQueryDto userQueryDto = userDao.findUserByUsername(username, Status.Y)
                .orElseThrow(() -> new UserNotFoundQueryResultException("아이디에 해당하는 유저가 없습니다."));
        return new UserDetailsImpl(UserModelConverter.from(userQueryDto));
    }

    @Override
    public CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username) {
        int count = userDao.checkDuplicateByUsername(username);
        return CheckDuplicateUsernameResponse.create(count);
    }

    @Override
    @Transactional
    public Integer register(RegisterRequest dto) {
        String encodedPassword = encoder.encodeByBCryptPasswordEncoder(dto.getPassword());
        String refreshToken = JwtProvider.makeRefreshToken(dto.getUsername());
        User user = dto.toModel(encodedPassword, refreshToken);
        int result = userDao.register(user);
        if (result != 1) {
            throw new UserInsertFailedException("user를 저장하는 과정에서 에러");
        }
        return result;
    }

    @Override
    public GetNicknameResponse getNickname(final Long userId) {
        String nickname = userDao.findNicknameByUserId(userId, Status.Y);
        if (!StringUtils.hasText(nickname)) {
            throw new UserNotFoundQueryResultException("유저 PK값에 해당 하는 닉네임이 없습니다.");
        }
        return GetNicknameResponse.create(nickname);
    }

//    @Override
//    public String findNicknameByUserId(Long userId) {
//        return userDao.findNicknameByUserId(userId);
//    }

}
