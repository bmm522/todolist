package com.jiinkim.todolist.common.exception.forbidden;

import com.jiinkim.todolist.common.exception.CustomException;

public class ForbiddenException extends CustomException {

  public ForbiddenException(final String message) {
    super(message);
  }
}
