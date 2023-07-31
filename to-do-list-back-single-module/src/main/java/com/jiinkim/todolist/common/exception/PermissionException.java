package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PermissionException extends CustomException {


    public PermissionException(final String message) {
        super(message);
    }
}
