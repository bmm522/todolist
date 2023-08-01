package com.jiinkim.todolist.common.jwt;


public class JwtProvider  {

    private static final JwtGenerator jwtGenerator = new JwtGenerator();
    private static final JwtTokenConverter jwtTokenConverter = new JwtTokenConverter();
    private static final JwtDecoder jwtDecoder = new JwtDecoder();

    private static final JwtValidator jwtValidator = new JwtValidator();
    private static JwtProperties jwtProperties;

    public static void setJwtProperties(JwtProperties jwtProperties) {
        JwtProvider.jwtProperties = jwtProperties;
    }


//    public static String getClaimStringByKeyFromAccessToken(final String accessToken, final String key) {
//        return jwtDecoder.getClaimStringByKeyFromAccessToken(accessToken, key, jwtProperties.getTokenPrefix(), jwtProperties.getSecret());
//    }
//
//    public static String getClaimStringByKeyFromRefreshToken(final String refreshToken, final String key) {
//        return jwtDecoder.getClaimStringByKeyFromRefreshToken(refreshToken, key, jwtProperties.getRefreshPrefix(), jwtProperties.getSecret());
//    }

    public static  Long getUserIdFromAccessToken(final String accessToken) {
        return jwtDecoder.getUserIdFromAccessToken(accessToken, jwtProperties.getTokenPrefix(), jwtProperties.getSecret());
    }

    public static String getUsernameFromAccessToken(final String accessToken) {
        return jwtDecoder.getUsernameFromJwtToken(accessToken, jwtProperties.getTokenPrefix(), jwtProperties.getSecret());
    }

    public static String getUsernameFromRefreshToken(final String refreshToken) {
        return jwtDecoder.getUsernameFromJwtToken(refreshToken, jwtProperties.getRefreshPrefix(), jwtProperties.getSecret());
    }


    public static JwtToken create(final String accessToken, final String refreshToken) {
        return jwtTokenConverter.of(accessToken, refreshToken);
    }

    public static JwtToken generatedJwtToken(final String accessToken) {
        return jwtTokenConverter.from(accessToken);
    }

    public static JwtToken  generatedJwtToken(final Long userId, final String username) {
        return jwtGenerator.make(userId, username, jwtProperties.getTokenPrefix(), jwtProperties.getRefreshPrefix(), jwtProperties.getIss(), jwtProperties.getSecret());
    }

    public static String generatedRefreshToken(final String username) {
        return jwtGenerator.makeRefreshToken(username,jwtProperties.getRefreshPrefix(), jwtProperties.getIss(), jwtProperties.getSecret());
    }

    public static String generatedAccessToken(final Long userId, final String username) {
        return jwtGenerator.makeAccessToken(userId,username,jwtProperties.getTokenPrefix(), jwtProperties.getIss(), jwtProperties.getSecret());
    }

    public static boolean isExpiredRefreshToken(final String jwtToken) {
        return jwtValidator.isExpiredJwtToken(jwtToken, jwtProperties.getSecret(), jwtProperties.getRefreshPrefix());
    }

    public static boolean isExpiredAccessToken(final String jwtToken) {
        return jwtValidator.isExpiredJwtToken(jwtToken, jwtProperties.getSecret(), jwtProperties.getTokenPrefix());
    }

    public static void checkValidAccessToken(final String jwtToken) {
        jwtValidator.checkValidJwtToken(jwtToken, jwtProperties.getTokenPrefix());
    }

    public static void checkValidRefreshToken(final String jwtToken) {
        jwtValidator.checkValidJwtToken(jwtToken, jwtProperties.getRefreshPrefix());
    }

}
