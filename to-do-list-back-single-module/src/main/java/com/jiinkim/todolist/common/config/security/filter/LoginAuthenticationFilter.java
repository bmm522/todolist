package com.jiinkim.todolist.common.config.security.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.common.config.security.filter.dto.UserInfoWithJwtToken;
import com.jiinkim.todolist.common.exception.servererror.UserNotFoundQueryResultException;
import com.jiinkim.todolist.common.exception.unauthorized.LoginFailedException;
import com.jiinkim.todolist.common.dto.ApiResponse;
import com.jiinkim.todolist.user.dao.UserDao;
import com.jiinkim.todolist.user.dao.model.UserModelConverter;
import com.jiinkim.todolist.user.dao.query.dto.UserQueryDto;
import com.jiinkim.todolist.common.jwt.JwtProvider;
import com.jiinkim.todolist.common.jwt.JwtToken;
import com.jiinkim.todolist.user.dao.model.User;
import com.jiinkim.todolist.user.service.dto.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@Slf4j
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        User user = null;

        try {
            user = objectMapper.readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            log.error("클라이언트로 부터 온 데이터 stream 과정에서 에러");
            log.error(e.getMessage());
        }

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        User loginUser = userDetails.getUser();

        User savedUser = getSavedUser(loginUser.getUsername());

        Optional<String> refreshTokenOp = reIssueTokenIfExpired(savedUser);

        JwtToken jwtToken = JwtProvider.generatedJwtToken(loginUser.getUserId(),
                loginUser.getUsername());
        refreshTokenOp.ifPresent(jwtToken::setRefreshToken);


        objectMapper.writeValue(response.getOutputStream(),
                ApiResponse.success(HttpStatus.OK,
                        UserInfoWithJwtToken.create(
                                jwtToken,
                                loginUser.getUserId(),
                                loginUser.getUsername(),
                                loginUser.getNickname())));
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        objectMapper.writeValue(response.getOutputStream(),
                ApiResponse.fail(HttpStatus.UNAUTHORIZED, new LoginFailedException("로그인을 실패했습니다.")));
    }

    private User getSavedUser(final String username) {
        UserQueryDto userQueryDto = userDao.findUserByUsername(username, Status.Y)
                .orElseThrow(() -> new UserNotFoundQueryResultException("아이디에 해당하는 유저가 없습니다."));
        return UserModelConverter.from(userQueryDto);
    }

    private Optional<String> reIssueTokenIfExpired(final User savedUser) {
        if (!JwtProvider.isExpiredRefreshToken(savedUser.getRefreshToken())) {

            String newRefreshToken = JwtProvider.generatedRefreshToken(savedUser.getUsername());

            savedUser.setRefreshToken(newRefreshToken);

            userDao.updateRefreshToken(savedUser);

            return Optional.of(newRefreshToken);

        }
        return Optional.empty();
    }
}
