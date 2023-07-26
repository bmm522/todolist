package com.jiinkim.todolist.user.jwt;

import jakarta.servlet.http.HttpServletRequest;

public class JwtConverter {

    public static JwtToken toJwtToken(final String accessToken, final String refreshToken) {
        return JwtToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public static JwtToken toJwtToken(final HttpServletRequest request) {
        String accessToken = request.getHeader("AccessToken");
        String refreshToken = request.getHeader("RefreshToken");
        return JwtToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


}
