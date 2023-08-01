package com.jiinkim.todolist.user.service.dto.converter;

import com.jiinkim.todolist.user.controller.dto.RegisterClientRequest;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;
import lombok.experimental.UtilityClass;


public interface UserServiceDtoConverter {

    RegisterRequest from(final RegisterClientRequest request);


}
