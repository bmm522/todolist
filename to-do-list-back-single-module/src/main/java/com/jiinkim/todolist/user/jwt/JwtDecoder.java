package com.jiinkim.todolist.user.jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;


public class JwtDecoder {


    public String getClaimStringByKeyFromAccessToken(final String accessToken, final String key, String tokenPrefix, final String secretKey) {
        String replaceAccessToken = replaceToken(accessToken, tokenPrefix);
        return getClaim(replaceAccessToken, key, secretKey).asString();
    }

    public String getClaimStringByKeyFromRefreshToken(final String refreshToken, final String key, final String refreshPrefix, final String secretKey) {
        String replaceRefreshToken = replaceToken(refreshToken, refreshPrefix);
        return getClaim(replaceRefreshToken, key, secretKey).asString();
    }

    public Long getUserIdFromAccessToken(final String accessToken, final String tokenPrefix, final String secretKey) {
        String replaceAccessToken = replaceToken(accessToken, tokenPrefix);
        return getClaim(replaceAccessToken, "userId", secretKey).asLong();
    }

    public String getUsernameFromAccessToken(final String accessToken, final String tokenPrefix, final String secretKey) {
        String replaceAccessToken = replaceToken(accessToken, tokenPrefix);
        return getClaim(replaceAccessToken, "username", secretKey).asString();
    }

    private  Claim getClaim(final String jwtToken, final String key, final String secretKey) {
        return JWT.require(Algorithm.HMAC256(secretKey)).build().verify(jwtToken).getClaim(key);
    }

    private  String replaceToken (final String token, final String prefix) {
            return token.replace(prefix, "");
    }


}
