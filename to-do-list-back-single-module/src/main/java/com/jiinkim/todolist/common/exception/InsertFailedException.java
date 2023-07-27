package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsertFailedException extends RuntimeException{

    String message;

    public InsertFailedException(String message) {
        this.message = message;
        log.error(message);
    }
}