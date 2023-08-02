package com.jiinkim.todolist.user.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UpdateNicknameRequest {

        private final String nickname;

        private final Long userId;

        public static UpdateNicknameRequest create(final String nickname, final Long userId) {

                return new UpdateNicknameRequest(nickname, userId);
        }

}
