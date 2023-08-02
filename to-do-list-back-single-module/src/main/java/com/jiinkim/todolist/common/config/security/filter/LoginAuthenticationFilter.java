package com.jiinkim.todolist.common.config.security.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.common.config.security.filter.dto.UserInfoWithJwtToken;
import com.jiinkim.todolist.common.exception.servererror.UserNotFoundQueryResultException;
import com.jiinkim.todolist.common.exception.unauthorized.LoginFailedException;
import com.jiinkim.todolist.common.dto.ApiResponse;
import com.jiinkim.todolist.user.dao.UserDao;
import com.jiinkim.todolist.user.dao.model.converter.UserModelConverter;
import com.jiinkim.todolist.user.dao.query.dto.UserQueryDto;
import com.jiinkim.todolist.common.jwt.JwtProvider;
import com.jiinkim.todolist.common.jwt.JwtToken;
import com.jiinkim.todolist.user.dao.model.User;
import com.jiinkim.todolist.common.config.security.UserDetailsImpl;
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

                // 클라이언트로부터 받은 유저 정보를 파싱합니다.
                User user = null;

                try {
                        user = objectMapper.readValue(request.getInputStream(), User.class);
                } catch (IOException e) {
                        log.error("클라이언트로 부터 온 데이터 stream 과정에서 에러");
                        log.error(e.getMessage());
                }

                // 인증 매니저에게 유저 정보를 전달하여 인증을 시도합니다.
                UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword());

                return authenticationManager.authenticate(authenticationToken);
        }

        /**
         * 인증이 성공한 경우 호출됩니다. JWT 토큰과 사용자 정보를 클라이언트로 응답합니다.
         */
        @Override
        protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain, Authentication authResult) throws IOException, ServletException {

                // 인증이 성공한 경우, 인증 결과로부터 유저 정보를 가져옵니다.
                UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
                User loginUser = userDetails.getUser();

                // 해당 유저의 refreshToken의 만료여부를 체크하기 위해 데이터베이스에서 유저 정보를 가져옵니다.
                User savedUser = getSavedUser(loginUser.getUsername());

                // 기존의 refreshToken이 만료되었을 땐, 빈값으로 반환, 만료가 안되었으면 String 값으로 전달
                Optional<String> refreshTokenOp = reIssueTokenIfExpired(savedUser);

                // refreshToken이 빈값 또는 String 일 수 있기에 세팅하는 작업입니다.
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
                    ApiResponse.fail(HttpStatus.UNAUTHORIZED,
                        new LoginFailedException("로그인을 실패했습니다.")));
        }

        private User getSavedUser(final String username) {

                UserQueryDto userQueryDto = userDao.findUserByUsername(username, Status.Y)
                    .orElseThrow(() -> new UserNotFoundQueryResultException("아이디에 해당하는 유저가 없습니다."));
                return UserModelConverter.from(userQueryDto);
        }

        private Optional<String> reIssueTokenIfExpired(final User savedUser) {

                if (!JwtProvider.isExpiredRefreshToken(savedUser.getRefreshToken())) {

                        String newRefreshToken = JwtProvider.generatedRefreshToken(
                            savedUser.getUsername());

                        savedUser.setRefreshToken(newRefreshToken);

                        userDao.updateRefreshToken(savedUser);

                        return Optional.of(newRefreshToken);

                }
                return Optional.empty();
        }

}
