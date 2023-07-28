package com.jiinkim.todolist.user.jwt;

import com.jiinkim.todolist.common.exception.InvalidTokenException;
import com.jiinkim.todolist.user.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Base64;
import java.util.Date;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Getter
@Setter
@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtToken {

  private String accessToken;
  private String refreshToken;
  private String tokenPrefix;
  private String refreshPrefix;
  private String secretKey;

  public static JwtToken create(final String accessToken, final String refreshToken, final String tokenPrefix, final String refreshPrefix, final String secretKey) {
    return new JwtToken(accessToken, refreshToken, tokenPrefix, refreshPrefix, secretKey);
  }


  /**
   * JWT 토큰의 유효성을 검사합니다.
   *
   * @throws InvalidTokenException 유효하지 않은 토큰인 경우 발생하는 예외
   */
  public void checkValidateJwtToken() {
    if (!StringUtils.hasText(accessToken) || !accessToken.startsWith(
        tokenPrefix)) {
      throw new InvalidTokenException("잘못된 토큰 정보입니다.");
    }
  }

  /**
   * 리프레시 토큰의 유효성을 검사합니다.
   *
   * @throws InvalidTokenException 유효하지 않은 토큰인 경우 발생하는 예외
   */
  public void checkValidateRefreshToken() {
    if (!StringUtils.hasText(refreshToken)|| !refreshToken.startsWith(
        refreshPrefix)) {
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
          .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
          .parseClaimsJws(accessToken.replace(tokenPrefix, ""));
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
          .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
          .parseClaimsJws(this.refreshToken.replace(refreshPrefix, ""));

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
