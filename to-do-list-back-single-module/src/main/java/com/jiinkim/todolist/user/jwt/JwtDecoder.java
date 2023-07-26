package com.jiinkim.todolist.user.jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.jiinkim.todolist.user.jwt.properties.JwtProperties;

public class JwtDecoder {



    public static String getClaimStringByKeyFromAccessToken(final String accessToken, final String key) {
        String replaceAccessToken = replaceToken(accessToken, JwtProperties.TOKEN_PREFIX);
        return getClaim(replaceAccessToken, key).asString();
    }

    public static String getClaimStringByKeyFromRefreshToken(final String refreshToken, final String key) {
        String replaceRefreshToken = replaceToken(refreshToken, JwtProperties.REFRESH_PREFIX);
        return getClaim(replaceRefreshToken, key).asString();
    }

    public static Long getUserIdFromAccessToken(final String accessToken) {
        return getClaim(accessToken, "userId").asLong();
    }

    private static Claim getClaim(final String jwtToken, final String key) {
        return JWT.require(Algorithm.HMAC256(JwtProperties.SECRET)).build().verify(jwtToken).getClaim(key);
    }

    private static String replaceToken (final String token, final String prefix) {
            return token.replace(prefix, "");
    }


}
