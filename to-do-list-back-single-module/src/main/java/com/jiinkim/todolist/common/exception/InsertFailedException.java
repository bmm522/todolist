package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsertFailedException extends CustomException {


    public InsertFailedException(final String message) {
        super(message);
    }
}
