package com.jiinkim.todolist.user.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiinkim.todolist.common.exception.LoginFailedException;
import com.jiinkim.todolist.common.exception.NotFoundQueryResultException;
import com.jiinkim.todolist.common.dto.ApiResponse;
import com.jiinkim.todolist.user.dao.UserDao;
import com.jiinkim.todolist.user.dao.model.UserConverter;
import com.jiinkim.todolist.user.dao.query.dto.UserQueryDto;
import com.jiinkim.todolist.user.jwt.JwtMaker;
import com.jiinkim.todolist.user.jwt.JwtToken;
import com.jiinkim.todolist.user.dao.model.User;
import com.jiinkim.todolist.user.service.dto.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = null;
        try {
            user = objectMapper.readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            log.error("클라이언트로 부터 온 데이터 stream 과정에서 에러");
            log.error(e.getMessage());
        }
        String username =user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);
        return   authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        User user = userDetails.getUser();
        JwtToken jwtToken = JwtMaker.create(user);

        UserQueryDto userQueryDto= userDao.findUserByUsername(user.getUsername())
                .orElseThrow(() -> new NotFoundQueryResultException("아이디에 해당하는 유저가 없습니다."));
        User savedUser = UserConverter.to(userQueryDto);
        String refreshTokenFromSavedUser = savedUser.getRefreshToken();
        jwtToken.setRefreshToken(refreshTokenFromSavedUser);

        if(!jwtToken.isRefreshTokenValid()) {
            String newRefreshToken = JwtMaker.makeRefreshToken(user.getUsername());
            savedUser.setRefreshToken(newRefreshToken);
            jwtToken.setRefreshToken(newRefreshToken);
            userDao.updateRefreshToken(savedUser);
        }

        objectMapper.writeValue(response.getOutputStream(),ApiResponse.success(HttpStatus.OK, jwtToken));
    }

    @Override
protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        objectMapper.writeValue(response.getOutputStream(), ApiResponse.fail(HttpStatus.UNAUTHORIZED, new LoginFailedException("로그인을 실패했습니다.")));
    }
}
