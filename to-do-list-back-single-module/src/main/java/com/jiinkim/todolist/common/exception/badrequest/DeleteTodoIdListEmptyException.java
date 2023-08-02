package com.jiinkim.todolist.common.exception.badrequest;

import com.jiinkim.todolist.common.exception.CustomException;

public class DeleteTodoIdListEmptyException extends BadRequestException {


        public DeleteTodoIdListEmptyException(final String message) {

                super(message);
        }

}
