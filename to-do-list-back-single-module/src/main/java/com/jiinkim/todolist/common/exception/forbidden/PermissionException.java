package com.jiinkim.todolist.common.exception.forbidden;

import com.jiinkim.todolist.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PermissionException extends ForbiddenException {


    public PermissionException(final String message) {
        super(message);
    }
}
