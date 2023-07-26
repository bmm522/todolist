package com.jiinkim.todolist.user.controller.util;

import com.jiinkim.todolist.user.controller.dto.RegisterClientRequest;
import com.jiinkim.todolist.user.service.dto.RegisterRequest;
import com.jiinkim.todolist.user.service.dto.UserDetailsImpl;
import com.jiinkim.todolist.user.service.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class DtoConverter {

  public static RegisterRequest toRegisterRequest(final RegisterClientRequest request) {
    return RegisterRequest.create(request.getUsername(), request.getPassword(), request.getNickname());
  }



}
