package com.jiinkim.todolist.common.exception.servererror;

import com.jiinkim.todolist.common.exception.CustomException;

public class InternalServerException extends CustomException {

    public InternalServerException(final String message) {
        super(message);
    }

}
