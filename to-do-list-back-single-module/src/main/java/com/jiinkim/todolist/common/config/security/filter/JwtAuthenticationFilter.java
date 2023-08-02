package com.jiinkim.todolist.common.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiinkim.todolist.common.dto.ApiResponse;
import com.jiinkim.todolist.common.exception.unauthorized.NotFoundTokenFromHeaderException;
import com.jiinkim.todolist.common.jwt.JwtProvider;
import com.jiinkim.todolist.common.config.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

import org.springframework.util.StringUtils;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

        private final ObjectMapper objectMapper;

        private final String headerName = "AccessToken";

        public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
            final ObjectMapper objectMapper) {

                super(authenticationManager);
                this.objectMapper = objectMapper;
        }

        /**
         * 만료된 AccessToken인 경우, 419 상태 코드로 응답합니다. 헤더에 AccessToken 정보가 없는 경우, 401 상태 코드로 응답합니다.
         */
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

                // 특정 URL 패턴에 대해 필터를 스킵하고 요청을 진행합니다.
                if (checkSkipFilter(request.getRequestURI())) {
                        chain.doFilter(request, response);
                        return;
                }

                String accessToken = getHeaderValue(request, response);

                JwtProvider.checkValidAccessToken(accessToken);

                // 만료되었을 때 419 코드 반환
                if (!JwtProvider.isExpiredAccessToken(accessToken)) {
                        handleExpired(response);
                        return;
                }

                UserDetailsImpl userDetails = getUserDetails(accessToken);

                Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null);

                SecurityContextHolder.getContext().setAuthentication(authentication);
                try {
                        chain.doFilter(request, response);
                } finally {
                        SecurityContextHolder.clearContext();
                }

        }


        private UserDetailsImpl getUserDetails(final String accessToken) {

                return new UserDetailsImpl(
                    JwtProvider.getUserIdFromAccessToken(accessToken),
                    JwtProvider.getUsernameFromAccessToken(accessToken)
                );
        }

        private void handleExpired(HttpServletResponse response)
            throws IOException {

                objectMapper.writeValue(response.getOutputStream(), ApiResponse.fail(419));
        }


        public boolean checkSkipFilter(final String requestURI) {

                return PermitUrls.isPermitted(requestURI);
        }

        private String getHeaderValue(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

                String headerValue = request.getHeader(headerName);
                if (!StringUtils.hasText(headerValue)) {
                        objectMapper.writeValue(response.getOutputStream(),
                            ApiResponse.fail(HttpStatus.UNAUTHORIZED,
                                new NotFoundTokenFromHeaderException(
                                    String.format("헤더에서 %s 정보를 찾을 수 없습니다.", headerName))));
                }
                return headerValue;
        }

}
