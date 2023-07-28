package com.jiinkim.todolist.user.jwt;

import org.springframework.stereotype.Component;


public class JwtTokenConverter {

    public JwtToken of(final String accessToken, final String refreshToken, final String tokenPrefix, final String refreshPrefix, final String secretKey) {
        return JwtToken.create(accessToken, refreshToken, tokenPrefix, refreshPrefix, secretKey);
    }

}
