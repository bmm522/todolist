package com.jiinkim.todolist.user.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jiinkim.todolist.user.jwt.properties.JwtProperties;
import com.jiinkim.todolist.user.dao.model.User;
import java.util.Date;

public class JwtMaker {


  private static final int EXPIRATION_TIME = 1000 * 60 * 60;
  private static final int REFRESHTOKEN_EXPIRATION_TIME = 14 * 24 * 6 * 10 * 60000;

  /**
   * 사용자 정보를 기반으로 JWT 토큰을 생성합니다.
   *
   * @param user 사용자 정보
   * @return 생성된 JwtToken 객체
   */
  public static JwtToken create(User user) {
    String accessToken = makeAccessToken(user.getUserId(),user.getUsername());
    String refreshToken = makeRefreshToken(user.getUsername());

    return JwtToken.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  public static String makeAccessToken(final Long userId, final String username) {
    return JwtProperties.TOKEN_PREFIX + JWT.create()
        .withIssuer(JwtProperties.ISS)
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .withClaim("userId", userId)
        .withClaim("username",username)
        .sign(Algorithm.HMAC256(JwtProperties.SECRET));
  }

  public static String makeRefreshToken(final String username) {
    return JwtProperties.REFRESH_PREFIX + JWT.create()
        .withSubject("refreshToken")
        .withIssuer(JwtProperties.ISS)
            .withClaim("username", username)
        .withExpiresAt(new Date(System.currentTimeMillis() + REFRESHTOKEN_EXPIRATION_TIME))
        .sign(Algorithm.HMAC256(JwtProperties.SECRET));
  }
}
