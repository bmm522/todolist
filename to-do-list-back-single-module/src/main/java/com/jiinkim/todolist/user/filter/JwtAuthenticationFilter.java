package com.jiinkim.todolist.user.filter;

import com.jiinkim.todolist.common.exception.NotFoundTokenFromHeaderException;
import com.jiinkim.todolist.user.jwt.JwtConverter;
import com.jiinkim.todolist.user.jwt.JwtDecoder;
import com.jiinkim.todolist.user.jwt.JwtToken;
import com.jiinkim.todolist.user.service.dto.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        if (checkSkipFilter(requestURI)) {
            log.info(requestURI);
            chain.doFilter(request, response);
            return;
        }

        String accessToken = getHeaderValue(request, "AccessToken");
        String refreshToken = getHeaderValue(request, "RefreshToken");

        JwtToken jwtToken = JwtConverter.toJwtToken(accessToken, refreshToken);

        jwtToken.checkValidateJwtToken();
        jwtToken.checkValidateRefreshToken();

        Long userId = JwtDecoder.getUserIdFromAccessToken(accessToken);
        String username = JwtDecoder.getUsernameFromAccessToken(accessToken);
        UserDetailsImpl userDetails = new UserDetailsImpl(userId, accessToken);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        request.setAttribute("AccessToken", accessToken);
        request.setAttribute("RefreshToken", refreshToken);
        try {
            chain.doFilter(request, response);// -> run to mvc
        }  finally {
            SecurityContextHolder.clearContext();
        }

    }

    public boolean checkSkipFilter(final String requestURI ) {
        return PermitUrls.isPermitted(requestURI);
    }

    private String getHeaderValue(HttpServletRequest request, String headerName) {
        String headerValue = request.getHeader(headerName);
        if (headerValue == null || headerValue.isEmpty()) {
            throw new NotFoundTokenFromHeaderException(
                    String.format("헤더에서 %s 정보를 찾을 수 없습니다.", headerName));
        }
        return headerValue;
    }
}
