package com.jiinkim.todolist.user.jwt;

import com.jiinkim.todolist.common.exception.InvalidTokenException;
import com.jiinkim.todolist.user.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Base64;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class JwtToken {

  private String accessToken;
  private String refreshToken;

  @Builder
  public JwtToken(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  /**
   * JWT 토큰의 유효성을 검사합니다.
   *
   * @throws InvalidTokenException 유효하지 않은 토큰인 경우 발생하는 예외
   */
  public void checkValidateJwtToken() {
    if (accessToken == null || accessToken.isEmpty() || !accessToken.startsWith(
        JwtProperties.TOKEN_PREFIX)) {
      throw new InvalidTokenException("잘못된 토큰 정보입니다.");
    }
  }

  /**
   * 리프레시 토큰의 유효성을 검사합니다.
   *
   * @throws InvalidTokenException 유효하지 않은 토큰인 경우 발생하는 예외
   */
  public void checkValidateRefreshToken() {
    if (refreshToken == null || refreshToken.isEmpty() || !refreshToken.startsWith(
        JwtProperties.REFRESH_PREFIX)) {
      throw new InvalidTokenException("잘못된 토큰 정보입니다.");
    }
  }

  /**
   * 액세스 토큰의 만료 여부를 확인합니다.
   *
   * @return 액세스 토큰이 만료되거나 문제가 있으면 false, 그렇지 않으면 true를 반환합니다.
   */
  public boolean isAccessTokenValid() {
    try {
      Jws<Claims> claims = Jwts.parser()
          .setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
          .parseClaimsJws(accessToken.replace(JwtProperties.TOKEN_PREFIX, ""));
      if (claims.getBody().getExpiration().before(new Date())) {
        return false;
      }
    } catch (JwtException e) {
      log.error(e.getMessage());
      throw new InvalidTokenException("잘못된 토큰 정보입니다.");
    }
    return true;
  }

  /**
   * 리프레시 토큰의 만료 여부를 확인합니다.
   *
   * @return 리프레시 토큰이 만료되거나 문제가 있으면  false, 그렇지 않으면 true를 반환합니다.
   */
  public boolean isRefreshTokenValid() {
    try {
      Jws<Claims> claims = Jwts.parser()
          .setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
          .parseClaimsJws(this.refreshToken.replace(JwtProperties.REFRESH_PREFIX, ""));

      if (claims.getBody().getExpiration().before(new Date())) {
        return false;
      }
    } catch (JwtException e) {
      log.error(e.getMessage());
      throw new InvalidTokenException("잘못된 토큰 정보입니다.");
    }

    return true;
  }
}
