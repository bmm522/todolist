package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundQueryResultException extends CustomException {


    public NotFoundQueryResultException(final String message) {
        super(message);
    }
}
