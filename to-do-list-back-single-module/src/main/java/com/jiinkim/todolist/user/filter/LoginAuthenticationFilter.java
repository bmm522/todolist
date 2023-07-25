package com.jiinkim.todolist.user.filter;


import com.jiinkim.todolist.common.exception.NotFoundEntityException;
import com.jiinkim.todolist.user.dao.UserDao;
import com.jiinkim.todolist.user.jwt.JwtMaker;
import com.jiinkim.todolist.user.jwt.JwtToken;
import com.jiinkim.todolist.user.jwt.JwtTokenCookie;
import com.jiinkim.todolist.user.jwt.JwtTokenCookieMaker;
import com.jiinkim.todolist.user.model.User;
import com.jiinkim.todolist.user.service.dto.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authenticationToken = null;
        try {
            authenticationToken =new UsernamePasswordAuthenticationToken(username, password);
        } catch(Exception e) {
            log.error("로그인 검증 필터 과정에서 에러");
            log.error(e.getMessage());
        }
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        User user = userDetails.getUser();
        JwtToken jwtToken = JwtMaker.create(user);

        User savedUser= userDao.findUserByUsername(user.getUsername()).orElseThrow(() -> new NotFoundEntityException("아이디에 해당하는 유저가 없습니다."));
        String refreshTokenFromSavedUser = savedUser.getRefreshToken();
        jwtToken.setRefreshToken(refreshTokenFromSavedUser);
        if(!jwtToken.isRefreshTokenValid()) {
            String newRefreshToken = JwtMaker.makeRefreshToken();
            savedUser.setRefreshToken(newRefreshToken);
            jwtToken.setRefreshToken(newRefreshToken);
            userDao.updateRefreshToken(savedUser);
        }
        JwtTokenCookie jwtTokenCookie = JwtTokenCookieMaker.INSTANCE.toCookie(jwtToken);
        response.addCookie(jwtTokenCookie.getAccessTokenCookie());
        response.addCookie(jwtTokenCookie.getRefreshTokenCookie());
        response.sendRedirect("http://localhost:9000/todo");
//        super.successfulAuthentication(request, response, chain, authResult);
    }
}
