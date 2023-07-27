package com.jiinkim.todolist.user.controller.dto;

import com.jiinkim.todolist.user.controller.dto.RegisterClientRequest;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserClientDtoConverter {

  public RegisterRequest from(final RegisterClientRequest request) {
    return RegisterRequest.create(request.getUsername(), request.getPassword(), request.getNickname());
  }



}
