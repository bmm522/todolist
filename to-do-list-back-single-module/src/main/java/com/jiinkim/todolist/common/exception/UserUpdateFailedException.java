package com.jiinkim.todolist.common.exception;

public class UserUpdateFailedException extends UpdateFailedException{

  public UserUpdateFailedException(final String message) {
    super(message);
  }
}
