package com.jiinkim.todolist.common.exception;

public class TodoNotFoundQueryResultException extends NotFoundQueryResultException{

  public TodoNotFoundQueryResultException(final String message) {
    super(message);
  }
}
