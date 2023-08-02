package com.jiinkim.todolist.common.exception.badrequest;

import com.jiinkim.todolist.common.exception.CustomException;

public class BadRequestException extends CustomException {

        public BadRequestException(final String message) {

                super(message);
        }

}
