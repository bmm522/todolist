package com.jiinkim.todolist.common.exception.servererror;

public class TodoInsertFailedException extends InternalServerException{

  public TodoInsertFailedException(final String message) {
    super(message);
  }
}
