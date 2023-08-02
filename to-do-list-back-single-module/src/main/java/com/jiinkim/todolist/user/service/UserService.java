package com.jiinkim.todolist.user.service;


import com.jiinkim.todolist.user.controller.dto.ReissueTokenRequest;
import com.jiinkim.todolist.user.service.dto.*;


public interface UserService {

        /**
         * 유저 아이디의 중복 여부를 체크합니다.
         *
         * @param username 중복 체크 하고자 하는 유저 아이디
         * @return CheckDuplicateUsernameResponse 중복 여부를 담은 응답 객체
         */
        CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username);

        /**
         * 회원가입을 합니다.
         *
         * @param toRegisterRequest 등록할 사용자 정보를 담은 RegisterRequest 객체
         * @return Integer 등록된 사용자의 아이디
         */
        Integer register(final RegisterRequest toRegisterRequest);

        /**
         * 사용자의 아이디로 닉네임을 조회합니다.
         *
         * @param userId 사용자의 아이디
         * @return GetNicknameResponse 조회된 닉네임을 담은 응답 객체
         */
        GetNicknameResponse getNickname(final Long userId);


        /**
         * 액세스 토큰을 재발급합니다.
         *
         * @param request 토큰 재발급 요청 데이터를 담은 ReissueTokenRequest 객체
         * @return ReissueTokenResponse 재발급된 토큰을 담은 응답 객체
         */
        ReissueTokenResponse reIssueToken(final ReissueTokenRequest request);

        /**
         * 사용자의 닉네임을 업데이트합니다.
         *
         * @param dto 업데이트할 닉네임 정보를 담은 UpdateNicknameRequest 객체
         * @return UpdateNicknameResponse 업데이트된 닉네임을 담은 응답 객체
         */
        UpdateNicknameResponse updateNickname(UpdateNicknameRequest dto);

}
