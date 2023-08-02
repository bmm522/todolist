package com.jiinkim.todolist.user.service;

import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.common.config.security.UserDetailsImpl;
import com.jiinkim.todolist.common.exception.forbidden.PermissionException;
import com.jiinkim.todolist.common.exception.servererror.UserInsertFailedException;
import com.jiinkim.todolist.common.exception.servererror.UserNotFoundQueryResultException;
import com.jiinkim.todolist.common.exception.servererror.UserUpdateFailedException;
import com.jiinkim.todolist.user.controller.dto.ReissueTokenRequest;
import com.jiinkim.todolist.user.dao.UserDao;
import com.jiinkim.todolist.user.dao.model.converter.UserModelConverter;
import com.jiinkim.todolist.user.dao.query.dto.UserQueryDto;
import com.jiinkim.todolist.user.dao.model.User;
import com.jiinkim.todolist.common.jwt.JwtProvider;
import com.jiinkim.todolist.user.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

        private final UserDao userDao;

        private final Encoder encoder;

        /**
         * 사용자명으로 사용자 정보를 조회하여 UserDetails 객체로 변환합니다.
         *
         * @param username 조회하고자 하는 사용자명
         * @return UserDetailsImpl 변환된 UserDetails 객체
         * @throws UsernameNotFoundException 사용자명에 해당하는 사용자를 찾을 수 없을 때 발생하는 예외
         */
        @Override
        public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

                User user = findUserByUsername(username);
                return new UserDetailsImpl(user);
        }

        /**
         * 사용자명의 중복 여부를 체크합니다.
         *
         * @param username 중복 체크 하고자 하는 사용자명
         * @return CheckDuplicateUsernameResponse 중복 여부를 담은 응답 객체
         */
        @Override
        public CheckDuplicateUsernameResponse checkDuplicatedUserId(final String username) {

                int count = userDao.checkDuplicateByUsername(username);
                return CheckDuplicateUsernameResponse.create(count);
        }

        /**
         * 새로운 사용자를 등록합니다.
         *
         * @param dto 등록할 사용자 정보를 담은 RegisterRequest 객체
         * @return Integer 등록된 사용자의 아이디
         * @throws UserInsertFailedException 사용자 등록 과정에서 에러가 발생한 경우
         */
        @Override
        @Transactional
        public Integer register(RegisterRequest dto) {

                String encodedPassword = encoder.encodeByBCryptPasswordEncoder(dto.getPassword());

                String refreshToken = JwtProvider.generatedRefreshToken(dto.getUsername());

                User user = dto.toModel(encodedPassword, refreshToken);

                int result = userDao.register(user);

                if (result != 1) {
                        throw new UserInsertFailedException("user를 저장하는 과정에서 에러");
                }
                return result;
        }

        /**
         * 사용자의 아이디로 닉네임을 조회합니다.
         *
         * @param userId 사용자의 아이디
         * @return GetNicknameResponse 조회된 닉네임을 담은 응답 객체
         * @throws UserNotFoundQueryResultException 사용자 아이디에 해당하는 사용자의 닉네임이 없을 경우 발생하는 예외
         */
        @Override
        public GetNicknameResponse getNickname(final Long userId) {

                String nickname = userDao.findNicknameByUserId(userId, Status.Y);
                if (!StringUtils.hasText(nickname)) {
                        throw new UserNotFoundQueryResultException("유저 PK값에 해당 하는 닉네임이 없습니다.");
                }

                return GetNicknameResponse.create(nickname);
        }

        /**
         * 액세스 토큰을 재발급합니다.
         *
         * @param request 토큰 재발급 요청 데이터를 담은 ReissueTokenRequest 객체
         * @return ReissueTokenResponse 재발급된 토큰을 담은 응답 객체
         */
        @Override
        public ReissueTokenResponse reIssueToken(final ReissueTokenRequest request) {

                JwtProvider.checkValidRefreshToken(request.getRefreshToken());

                String username = JwtProvider.getUsernameFromRefreshToken(
                    request.getRefreshToken());

                User user = findUserByUsername(username);

                String refreshToken = reIssueTokenIfExpired(user)
                    .orElse(user.getRefreshToken());

                String accessToken = JwtProvider.generatedAccessToken(user.getUserId(),
                    user.getUsername());

                return ReissueTokenResponse.create(accessToken, refreshToken);
        }

        /**
         * 사용자의 닉네임을 업데이트합니다.
         *
         * @param request 업데이트할 닉네임 정보를 담은 UpdateNicknameRequest 객체
         * @return UpdateNicknameResponse 업데이트된 닉네임을 담은 응답 객체
         * @throws UserNotFoundQueryResultException 사용자 아이디에 해당하는 사용자가 없는 경우
         * @throws PermissionException              닉네임을 변경할 권한이 없는 경우
         * @throws UserUpdateFailedException        사용자 닉네임 업데이트 과정에서 에러가 발생한 경우
         */
        @Override
        @Transactional
        public UpdateNicknameResponse updateNickname(UpdateNicknameRequest request) {

                UserQueryDto userQueryDto = userDao.findUserByUserId(request.getUserId(), Status.Y)
                    .orElseThrow(
                        () -> new UserNotFoundQueryResultException("유저 PK값에 해당하는 유저가 없습니다."));

                if (!userQueryDto.isPermission(request.getUserId())) {
                        throw new PermissionException("유저 닉네임을 변경할 권한이 없습니다.");
                }

                User user = UserModelConverter.from(userQueryDto);

                user.updateNickname(request.getNickname());

                int result = userDao.updateNickname(user);

                if (result != 1) {
                        throw new UserUpdateFailedException("user의 nickname을 업데이트 하는 과정에서 에러");
                }

                return UpdateNicknameResponse.create(request.getNickname());
        }

        private Optional<String> reIssueTokenIfExpired(final User savedUser) {

                if (!JwtProvider.isExpiredRefreshToken(savedUser.getRefreshToken())) {

                        String newRefreshToken = JwtProvider.generatedRefreshToken(
                            savedUser.getUsername());

                        savedUser.setRefreshToken(newRefreshToken);

                        userDao.updateRefreshToken(savedUser);

                        return Optional.of(newRefreshToken);

                }
                return Optional.empty();
        }

        private User findUserByUsername(final String username) {

                UserQueryDto userQueryDto = userDao.findUserByUsername(username, Status.Y)
                    .orElseThrow(() -> new UserNotFoundQueryResultException("아이디에 해당하는 유저가 없습니다."));
                return UserModelConverter.from(userQueryDto);
        }


}
