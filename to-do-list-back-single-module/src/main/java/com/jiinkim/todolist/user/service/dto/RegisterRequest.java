package com.jiinkim.todolist.user.service.dto;

import lombok.Getter;

@Getter
public class RegisterRequest {

  private String username;
  private String password;

  private RegisterRequest(final String username, final String password) {
    this.username = username;
    this.password = password;
  }

  public static RegisterRequest create(final String username, final String password) {
    return new RegisterRequest(username, password);
  }

  public void encodedPassword(String encodedPassword) {
    this.password = encodedPassword;
  }
}
