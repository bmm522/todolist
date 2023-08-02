package com.jiinkim.todolist.common.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtTokenConverter {

        protected JwtToken of(final String accessToken, final String refreshToken) {

                return JwtToken.create(accessToken, refreshToken);
        }

        protected JwtToken from(final String accessToken) {

                return JwtToken.createAccessToken(accessToken);
        }

}
