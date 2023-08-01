package com.jiinkim.todolist.common.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiinkim.todolist.common.dto.ApiResponse;
import com.jiinkim.todolist.common.exception.AccessTokenExpiredException;
import com.jiinkim.todolist.common.exception.unauthorized.NotFoundTokenFromHeaderException;
import com.jiinkim.todolist.common.jwt.JwtProvider;
import com.jiinkim.todolist.user.service.dto.UserDetailsImpl;
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

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    if (checkSkipFilter(request.getRequestURI())) {
      chain.doFilter(request, response);
      return;
    }

    String accessToken = getHeaderValue(request, response);

    JwtProvider.checkValidAccessToken(accessToken);

    if (!JwtProvider.isExpiredAccessToken(accessToken)) {
      handleExpired(response);
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
    objectMapper.writeValue(response.getOutputStream(), ApiResponse.fail(HttpStatus.UNAUTHORIZED,
        AccessTokenExpiredException.class));
  }


  public boolean checkSkipFilter(final String requestURI) {
    return PermitUrls.isPermitted(requestURI);
  }

  private String getHeaderValue(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String headerValue = request.getHeader(headerName);
    if (StringUtils.hasText(headerValue)) {
      objectMapper.writeValue(response.getOutputStream(),
          ApiResponse.fail(HttpStatus.UNAUTHORIZED, new NotFoundTokenFromHeaderException(
              String.format("헤더에서 %s 정보를 찾을 수 없습니다.", headerName))));
    }
    return headerValue;
  }
}
