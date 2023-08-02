package com.jiinkim.todolist.user.service.dto.converter;

import com.jiinkim.todolist.user.controller.dto.RegisterClientRequest;
import com.jiinkim.todolist.user.controller.dto.UpdateNicknameClientRequest;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;
import com.jiinkim.todolist.user.service.dto.UpdateNicknameRequest;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@UtilityClass
public class UserServiceDtoConverter {


        public RegisterRequest from(final RegisterClientRequest request) {

                return RegisterRequest.create(request.getUsername(), request.getPassword(),
                    request.getNickname());
        }


        public static UpdateNicknameRequest of(final UpdateNicknameClientRequest request,
            final Long userId) {

                return UpdateNicknameRequest.create(request.getNickname(), userId);
        }

}
