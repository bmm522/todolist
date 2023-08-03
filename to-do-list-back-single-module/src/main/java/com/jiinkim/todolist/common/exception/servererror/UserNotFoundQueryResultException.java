package com.jiinkim.todolist.common.exception.servererror;

public class UserNotFoundQueryResultException extends InternalServerException {

    public UserNotFoundQueryResultException(final String message) {
        super(message);
    }

}
