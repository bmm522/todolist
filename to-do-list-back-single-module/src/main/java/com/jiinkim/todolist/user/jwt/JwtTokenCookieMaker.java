package com.jiinkim.todolist.user.jwt;

import jakarta.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public enum JwtTokenCookieMaker {

  INSTANCE;

  /**
   * 주어진 JWT 토큰을 인코딩하여 쿠키로 변환합니다.
   *
   * @param jwtToken JWT 토큰
   * @return JWT 토큰이 포함된 쿠키 객체
   * @throws UnsupportedEncodingException 인코딩 예외가 발생한 경우
   */
  public JwtTokenCookie toCookie(JwtToken jwtToken) throws UnsupportedEncodingException {
    String access = setEncode(jwtToken.getAccessToken());
    String refresh = setEncode(jwtToken.getRefreshToken());

    return JwtTokenCookie.create(getCookie("AccessToken", access, 600),
        getCookie("RefreshToken", refresh, 600));
  }

//  public JwtTokenCookie toCookieWhenLogout() {
//    return JwtTokenCookie.create(getCookie("AccessToken", "", 0),
//        getCookie("RefreshToken", "", 0));
//  }

  public String setEncode(String token) throws UnsupportedEncodingException {
    return URLEncoder.encode(token, StandardCharsets.UTF_8);
  }


  public Cookie getCookie(String cookieName, String cookieValue, int expiredTime) {
    Cookie cookie = new Cookie(cookieName, cookieValue);
    cookie.setPath("/");
    cookie.setMaxAge(expiredTime);
    return cookie;
  }

//	public Cookie getCookie(String cookieName, String cookieValue) {
//		Cookie cookie = new Cookie(cookieName, cookieValue);
//		cookie.setPath("/");
//		cookie.setDomain(".quizstudio.site");
//		cookie.setSecure(true);
//		return cookie;
//	}
}
