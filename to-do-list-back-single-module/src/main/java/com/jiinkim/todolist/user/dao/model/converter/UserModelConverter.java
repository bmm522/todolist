package com.jiinkim.todolist.user.dao.model.converter;

import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.user.dao.model.User;
import com.jiinkim.todolist.user.dao.query.dto.UserQueryDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserModelConverter {


        public User from(UserQueryDto dto) {

                return User.create(dto.getUserId(), dto.getUsername(), dto.getPassword(),
                    dto.getNickname(), dto.getRefreshToken(), Status.Y, dto.getCreatedAt(),
                    dto.getUpdatedAt());
        }

}
