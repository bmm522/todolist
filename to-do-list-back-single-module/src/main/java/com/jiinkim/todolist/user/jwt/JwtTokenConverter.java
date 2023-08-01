package com.jiinkim.todolist.user.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtTokenConverter {

    protected JwtToken of(final String accessToken, final String refreshToken, final String tokenPrefix, final String refreshPrefix, final String secretKey) {
        return JwtToken.create(accessToken, refreshToken, tokenPrefix, refreshPrefix, secretKey);
    }

}
