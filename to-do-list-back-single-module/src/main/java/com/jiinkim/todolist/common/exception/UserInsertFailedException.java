package com.jiinkim.todolist.common.exception;

public class UserInsertFailedException extends InsertFailedException{

  public UserInsertFailedException(final String message) {
    super(message);
  }
}
