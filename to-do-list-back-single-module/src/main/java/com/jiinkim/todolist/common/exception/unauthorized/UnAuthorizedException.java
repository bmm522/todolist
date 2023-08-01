package com.jiinkim.todolist.common.exception.unauthorized;

import com.jiinkim.todolist.common.exception.CustomException;

public class UnAuthorizedException extends CustomException {

  public UnAuthorizedException(final String message) {
    super(message);
  }
}
