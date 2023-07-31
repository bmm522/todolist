package com.jiinkim.todolist.common.exception;

public class TodoUpdateFailedException extends UpdateFailedException {

  public TodoUpdateFailedException(final String message) {
    super(message);
  }
}
