package com.jiinkim.todolist.common.exception.servererror;



public class TodoNotFoundQueryResultException extends InternalServerException {

  public TodoNotFoundQueryResultException(final String message) {
    super(message);
  }
}
