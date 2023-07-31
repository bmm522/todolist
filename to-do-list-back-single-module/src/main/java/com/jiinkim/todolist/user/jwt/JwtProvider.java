package com.jiinkim.todolist.user.jwt;


public class JwtProvider  {

    private static final JwtGenerator jwtGenerator = new JwtGenerator();
    private static final JwtTokenConverter jwtTokenConverter = new JwtTokenConverter();
    private static final JwtDecoder jwtDecoder = new JwtDecoder();
    private static JwtProperties jwtProperties;

    public static void setJwtProperties(JwtProperties jwtProperties) {
        JwtProvider.jwtProperties = jwtProperties;
    }


    public static String getClaimStringByKeyFromAccessToken(final String accessToken, final String key) {
        return jwtDecoder.getClaimStringByKeyFromAccessToken(accessToken, key, jwtProperties.getTokenPrefix(), jwtProperties.getSecret());
    }

    public static String getClaimStringByKeyFromRefreshToken(final String refreshToken, final String key) {
        return jwtDecoder.getClaimStringByKeyFromRefreshToken(refreshToken, key, jwtProperties.getRefreshPrefix(), jwtProperties.getSecret());
    }

    public static  Long getUserIdFromAccessToken(final String accessToken) {
        return jwtDecoder.getUserIdFromAccessToken(accessToken, jwtProperties.getTokenPrefix(), jwtProperties.getSecret());
    }

    public static String getUsernameFromAccessToken(final String accessToken) {
        return jwtDecoder.getUsernameFromAccessToken(accessToken, jwtProperties.getTokenPrefix(), jwtProperties.getSecret());
    }

    public static JwtToken create(final String accessToken, final String refreshToken) {
        return jwtTokenConverter.of(accessToken, refreshToken, jwtProperties.getTokenPrefix(), jwtProperties.getRefreshPrefix(), jwtProperties.getSecret());
    }

    public static JwtToken create(final Long userId, final String username) {
        return jwtGenerator.make(userId, username, jwtProperties.getTokenPrefix(), jwtProperties.getRefreshPrefix(), jwtProperties.getIss(), jwtProperties.getSecret());
    }

    public static String makeRefreshToken(final String username) {
        return jwtGenerator.makeRefreshToken(username,jwtProperties.getRefreshPrefix(), jwtProperties.getIss(), jwtProperties.getSecret());
    }

}
