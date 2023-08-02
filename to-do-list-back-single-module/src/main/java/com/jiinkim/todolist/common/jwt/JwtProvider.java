package com.jiinkim.todolist.common.jwt;


import com.jiinkim.todolist.common.exception.unauthorized.InvalidTokenException;

public class JwtProvider {

        private static final JwtGenerator jwtGenerator = new JwtGenerator();

        private static final JwtTokenConverter jwtTokenConverter = new JwtTokenConverter();

        private static final JwtDecoder jwtDecoder = new JwtDecoder();

        private static final JwtValidator jwtValidator = new JwtValidator();

        private static JwtProperties jwtProperties;

        public static void setJwtProperties(JwtProperties jwtProperties) {

                JwtProvider.jwtProperties = jwtProperties;
        }


        /**
         * AccessToken으로부터 사용자 ID를 추출합니다.
         *
         * @param accessToken JWT Access Token
         * @return 사용자 ID
         */
        public static Long getUserIdFromAccessToken(final String accessToken) {

                return jwtDecoder.getUserIdFromAccessToken(accessToken,
                    jwtProperties.getTokenPrefix(), jwtProperties.getSecret());
        }

        /**
         * AccessToken으로부터 사용자 이름을 추출합니다.
         *
         * @param accessToken JWT Access Token
         * @return 사용자 이름
         */
        public static String getUsernameFromAccessToken(final String accessToken) {

                return jwtDecoder.getUsernameFromJwtToken(accessToken,
                    jwtProperties.getTokenPrefix(), jwtProperties.getSecret());
        }

        /**
         * RefreshToken으로부터 사용자 이름을 추출합니다.
         *
         * @param refreshToken JWT Refresh Token
         * @return 사용자 이름
         */
        public static String getUsernameFromRefreshToken(final String refreshToken) {

                return jwtDecoder.getUsernameFromJwtToken(refreshToken,
                    jwtProperties.getRefreshPrefix(), jwtProperties.getSecret());
        }


        /**
         * AccessToken과 RefreshToken으로 JwtToken 객체를 생성합니다.
         *
         * @param accessToken  JWT Access Token
         * @param refreshToken JWT Refresh Token
         * @return JwtToken 객체
         */
        public static JwtToken create(final String accessToken, final String refreshToken) {

                return jwtTokenConverter.of(accessToken, refreshToken);
        }

        /**
         * 주어진 AccessToken을 이용하여 JwtToken 객체를 생성합니다.
         *
         * @param accessToken JWT Access Token
         * @return JwtToken 객체
         */
        public static JwtToken generatedJwtToken(final String accessToken) {

                return jwtTokenConverter.from(accessToken);
        }

        /**
         * 주어진 사용자 ID와 이름을 이용하여 JwtToken 객체를 생성합니다.
         *
         * @param userId   사용자 ID
         * @param username 사용자 이름
         * @return JwtToken 객체
         */
        public static JwtToken generatedJwtToken(final Long userId, final String username) {

                return jwtGenerator.make(userId, username, jwtProperties.getTokenPrefix(),
                    jwtProperties.getRefreshPrefix(), jwtProperties.getIss(),
                    jwtProperties.getSecret());
        }

        /**
         * 주어진 사용자 이름을 이용하여 RefreshToken을 생성합니다.
         *
         * @param username 사용자 이름
         * @return 생성된 Refresh Token
         */
        public static String generatedRefreshToken(final String username) {

                return jwtGenerator.makeRefreshToken(username, jwtProperties.getRefreshPrefix(),
                    jwtProperties.getIss(), jwtProperties.getSecret());
        }

        /**
         * 주어진 사용자 ID와 이름을 이용하여 AccessToken을 생성합니다.
         *
         * @param userId   사용자 ID
         * @param username 사용자 이름
         * @return 생성된 Access Token
         */
        public static String generatedAccessToken(final Long userId, final String username) {

                return jwtGenerator.makeAccessToken(userId, username,
                    jwtProperties.getTokenPrefix(), jwtProperties.getIss(),
                    jwtProperties.getSecret());
        }

        /**
         * 주어진 Refresh Token이 만료되었는지 확인합니다.
         *
         * @param jwtToken JWT Token (Refresh Token)
         * @return 만료 여부 (false: 만료, true: 유효)
         */
        public static boolean isExpiredRefreshToken(final String jwtToken) {

                return jwtValidator.isExpiredJwtToken(jwtToken, jwtProperties.getSecret(),
                    jwtProperties.getRefreshPrefix());
        }

        /**
         * 주어진 Access Token이 만료되었는지 확인합니다.
         *
         * @param jwtToken JWT Token (Access Token)
         * @return 만료 여부 (false: 만료, true: 유효)
         */
        public static boolean isExpiredAccessToken(final String jwtToken) {

                return jwtValidator.isExpiredJwtToken(jwtToken, jwtProperties.getSecret(),
                    jwtProperties.getTokenPrefix());
        }

        /**
         * 주어진 Access Token의 유효성을 확인합니다.
         *
         * @param jwtToken JWT Token (Access Token)
         * @throws InvalidTokenException AccessToken의 유효성 검증에 실패한 경우 예외를 던집니다.
         */
        public static void checkValidAccessToken(final String jwtToken) {

                jwtValidator.checkValidJwtToken(jwtToken, jwtProperties.getTokenPrefix());
        }

        /**
         * 주어진 Refresh Token의 유효성을 확인합니다.
         *
         * @param jwtToken JWT Token (Refresh Token)
         * @throws InvalidTokenException RefreshToken의 유효성 검증에 실패한 경우 예외를 던집니다.
         */
        public static void checkValidRefreshToken(final String jwtToken) {

                jwtValidator.checkValidJwtToken(jwtToken, jwtProperties.getRefreshPrefix());
        }

}
