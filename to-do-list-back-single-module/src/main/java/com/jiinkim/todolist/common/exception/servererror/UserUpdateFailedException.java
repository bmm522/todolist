package com.jiinkim.todolist.common.exception.servererror;

public class UserUpdateFailedException extends InternalServerException {

  public UserUpdateFailedException(final String message) {
    super(message);
  }
}
