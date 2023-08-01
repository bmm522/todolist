package com.jiinkim.todolist.common.exception.servererror;

public class UserInsertFailedException extends InternalServerException {

  public UserInsertFailedException(final String message) {
    super(message);
  }
}
