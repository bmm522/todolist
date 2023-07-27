package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidTokenException extends RuntimeException{

  String message;

  public InvalidTokenException(String message) {
    this.message = message;
    log.error(message);
  }
}