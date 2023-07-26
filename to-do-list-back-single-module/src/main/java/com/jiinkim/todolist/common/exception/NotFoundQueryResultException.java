package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundQueryResultException extends RuntimeException {

    String message;

    public NotFoundQueryResultException(String message) {
        this.message = message;
        log.error(message);
    }
}
