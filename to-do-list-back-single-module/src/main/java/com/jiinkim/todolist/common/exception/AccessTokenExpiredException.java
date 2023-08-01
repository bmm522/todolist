package com.jiinkim.todolist.common.exception;

public class AccessTokenExpiredException extends CustomException{

  public AccessTokenExpiredException(final String message) {
    super(message);
  }
}
