package com.jiinkim.todolist.user.controller.util;

import com.jiinkim.todolist.user.controller.dto.RegisterClientRequest;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;

public class DtoConverter {

  public static RegisterRequest from(final RegisterClientRequest request) {
    return RegisterRequest.create(request.getUsername(), request.getPassword(), request.getNickname());
  }



}
