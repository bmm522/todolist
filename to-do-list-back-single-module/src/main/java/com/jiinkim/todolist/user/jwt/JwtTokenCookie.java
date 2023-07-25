package com.jiinkim.todolist.user.jwt;

import jakarta.servlet.http.Cookie;

public class JwtTokenCookie {

  private final Cookie accessTokenCookie;
  private final Cookie refreshTokenCookie;

  private JwtTokenCookie(Cookie accessTokenCookie, Cookie refreshTokenCookie) {
    this.accessTokenCookie = accessTokenCookie;
    this.refreshTokenCookie = refreshTokenCookie;
  }

  public static JwtTokenCookie create(Cookie authorizationCookie, Cookie refreshTokenCookie) {
    return new JwtTokenCookie(authorizationCookie, refreshTokenCookie);
  }

  public Cookie getAccessTokenCookie() {
    return accessTokenCookie;
  }

  public Cookie getRefreshTokenCookie() {
    return refreshTokenCookie;
  }
}
