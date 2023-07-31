package com.jiinkim.todolist.common.exception;

public class UserNotFoundQueryResultException extends NotFoundQueryResultException{

  public UserNotFoundQueryResultException(final String message) {
    super(message);
  }
}
