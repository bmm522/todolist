package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundTokenFromHeaderException extends CustomException {


    public NotFoundTokenFromHeaderException(final String message) {
        super(message);
    }
}
