package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateFailedException extends RuntimeException {

    private String message;

    public UpdateFailedException(String message) {
        this.message = message;
        log.error(message);
    }
}
