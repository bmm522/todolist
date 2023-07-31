package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundTokenFromHeaderException extends RuntimeException {

    private String message;

    public NotFoundTokenFromHeaderException(String message) {
        this.message = message;
        log.error(message);
    }
}
