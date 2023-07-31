package com.jiinkim.todolist.common.exception;

public class TodoInsertFailedException extends InsertFailedException{

  public TodoInsertFailedException(final String message) {
    super(message);
  }
}
