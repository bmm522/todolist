package com.jiinkim.todolist.user.service.dto;

import com.jiinkim.todolist.user.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterRequest {

  private final String username;
  private String password;

  public static RegisterRequest create(final String username, final String password) {
    return new RegisterRequest(username, password);
  }

  public void setPassword(String encodedPassword) {
    this.password = encodedPassword;
  }


  public User toModel(LocalDateTime now, String encodedPassword, String refreshToken) {
    User user = User.createWhenRegister(this.username, encodedPassword);
    user.setRefreshToken(refreshToken);
    user.setCreatedAt(now);
    user.setUpdatedAt(now);
    return user;
  }
}
