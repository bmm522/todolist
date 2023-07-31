package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidTokenException extends CustomException {

    public InvalidTokenException(final String message) {
        super(message);
    }
}
