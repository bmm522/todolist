package com.jiinkim.todolist.user.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateNicknameResponse {

        private final String updatedNickname;

        public static UpdateNicknameResponse create(final String updatedNickname) {

                return new UpdateNicknameResponse(updatedNickname);
        }

}
