package com.jiinkim.todolist.user.service.dto;

import com.jiinkim.todolist.user.dao.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterRequest {

  private final String username;
  private String password;
  private final String nickname;

  public static RegisterRequest create(final String username, final String password, final String nickname) {
    return new RegisterRequest(username, password, nickname);
  }

  public User toModel(final String encodedPassword, final String refreshToken) {
    User user = User.createWhenRegister(username, encodedPassword, nickname);
    user.setRefreshToken(refreshToken);
    return user;
  }
}
