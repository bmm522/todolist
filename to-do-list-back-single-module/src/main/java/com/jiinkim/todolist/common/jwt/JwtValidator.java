package com.jiinkim.todolist.common.jwt;


import com.jiinkim.todolist.common.exception.unauthorized.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Base64;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtValidator {


  /**
   * 토큰의 만료 여부를 확인합니다.
   *
   * @return 토큰이 만료면  false, 그렇지 않으면 true를 반환합니다.
   */
  protected boolean isExpiredJwtToken(final String jwtToken, final String secretKey, final String tokenPrefix) {
    try {
      Jwts.parser()
          .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
          .parseClaimsJws(jwtToken.replace(tokenPrefix, ""));
    } catch (ExpiredJwtException e) {
      return false;
    } catch (JwtException e) {
      throw new InvalidTokenException("잘못된 토큰 정보입니다.");
    }

    return true;
  }

  /**
   * 토큰의 유효성을 검사합니다.
   *
   * @throws InvalidTokenException 유효하지 않은 토큰인 경우 발생하는 예외
   */
  public void checkValidJwtToken(final String jwtToken, final String tokenPrefix) {
    if (!StringUtils.hasText(jwtToken) || !jwtToken.startsWith(
        tokenPrefix)) {
      throw new InvalidTokenException("잘못된 토큰 정보입니다.");
    }
  }
}
