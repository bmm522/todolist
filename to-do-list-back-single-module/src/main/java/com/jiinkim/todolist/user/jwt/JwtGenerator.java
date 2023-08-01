package com.jiinkim.todolist.user.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtGenerator {


    private final int EXPIRATION_TIME = 1000 * 60 * 60 * 60 * 60;
    private final int REFRESHTOKEN_EXPIRATION_TIME = 14 * 24 * 6 * 10 * 60000;

    /**
     * 사용자 정보를 기반으로 JWT 토큰을 생성합니다.
     *
     * @param
     * @return 생성된 JwtToken 객체
     */
    protected JwtToken make(final Long userId, final String username, final String tokenPrefix, final String refreshPrefix, final String iss, final String secretKey) {
        String accessToken = makeAccessToken(userId, username, tokenPrefix, iss, secretKey);
        String refreshToken = makeRefreshToken(username, refreshPrefix, iss, secretKey);

        return JwtToken.create(accessToken, refreshToken, tokenPrefix, refreshPrefix, secretKey);
    }

    protected String makeAccessToken(final Long userId, final String username, final String tokenPrefix, final String iss, final String secretKey) {
        return tokenPrefix + JWT.create()
                .withIssuer(iss)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("userId", userId)
                .withClaim("username", username)
                .sign(Algorithm.HMAC256(secretKey));
    }

    protected String makeRefreshToken(final String username, final String refreshPrefix, final String iss, final String secretKey) {
        return refreshPrefix + JWT.create()
                .withSubject("refreshToken")
                .withIssuer(iss)
                .withClaim("userId", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESHTOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(secretKey));
    }
}
