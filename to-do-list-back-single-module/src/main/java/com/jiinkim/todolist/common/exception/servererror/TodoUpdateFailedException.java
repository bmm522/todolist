package com.jiinkim.todolist.common.exception.servererror;

public class TodoUpdateFailedException extends InternalServerException {

    public TodoUpdateFailedException(final String message) {
        super(message);
    }

}
