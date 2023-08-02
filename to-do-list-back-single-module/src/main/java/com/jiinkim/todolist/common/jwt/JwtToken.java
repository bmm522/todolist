package com.jiinkim.todolist.common.jwt;

import com.jiinkim.todolist.common.exception.unauthorized.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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


        public JwtToken(final String accessToken) {

                this.accessToken = accessToken;

        }

        public static JwtToken createAccessToken(final String accessToken) {

                return new JwtToken(accessToken);
        }

        public static JwtToken create(final String accessToken, final String refreshToken) {

                return new JwtToken(accessToken, refreshToken);
        }


}
