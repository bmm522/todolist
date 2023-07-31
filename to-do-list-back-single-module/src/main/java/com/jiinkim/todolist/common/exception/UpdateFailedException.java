package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateFailedException extends CustomException {


    public UpdateFailedException(final String message) {
        super(message);
    }
}
