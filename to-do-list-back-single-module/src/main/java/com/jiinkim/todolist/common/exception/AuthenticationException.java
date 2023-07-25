package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationException extends Exception{

  String message;

  public AuthenticationException(String message) {
    this.message = message;
    log.error(message);
  }
}
