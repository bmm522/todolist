package com.jiinkim.todolist.user.service.dto.converter;

import com.jiinkim.todolist.user.controller.dto.RegisterClientRequest;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class UserServiceDtoConverterImpl implements UserServiceDtoConverter {

    @Override
    public RegisterRequest from(final RegisterClientRequest request) {
        return RegisterRequest.create(request.getUsername(), request.getPassword(), request.getNickname());
    }


}
