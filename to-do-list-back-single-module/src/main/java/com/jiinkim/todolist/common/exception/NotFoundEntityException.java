package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundEntityException extends RuntimeException {

    String message;

    public NotFoundEntityException(String message) {
        this.message = message;
        log.error(message);
    }
}
