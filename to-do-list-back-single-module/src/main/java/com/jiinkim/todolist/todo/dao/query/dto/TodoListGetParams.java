package com.jiinkim.todolist.todo.dao.query.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Alias("todoListGetParams")
public class TodoListGetParams {

        private int page;

        private int pageSize;

        private final Long userId;

        private final Status isGetBeforeDataStatus;

        private final String todoTitle;

        private final String fromTodoAt;

        private final String toTodoAt;

        private Status isUpdate;

        private Status status;


        private TodoListGetParams(final int page, final Long userId, final Status isUpdate,
            final Status isGetBeforeDataStatus, final String todoTitle, final String fromTodoAt,
            final String toTodoAt) {

                this.page = page;
                this.pageSize = 10;
                this.userId = userId;
                this.status = Status.Y;
                this.isUpdate = isUpdate;
                this.isGetBeforeDataStatus = isGetBeforeDataStatus;
                this.todoTitle = todoTitle;
                this.fromTodoAt = fromTodoAt;
                this.toTodoAt = toTodoAt;
        }

        public static TodoListGetParams create(final int page, final Long userId,
            final Status isUpdate, final Status isGetBeforeDataStatus, final String todoTitle,
            final String fromTodoAt, final String toTodoAt) {

                return new TodoListGetParams(page, userId, isUpdate, isGetBeforeDataStatus,
                    todoTitle, fromTodoAt, toTodoAt);
        }

        public int getOffset() {

                return (this.page - 1) * this.pageSize;
        }

        public int getStartRow() {

                return (page - 1) * pageSize + 1;
        }

        public String getIsUpdate() {

                return isUpdate.toString();
        }

        public String getIsGetBeforeDataStatus() {

                return isGetBeforeDataStatus.toString();
        }

        public void setPageSize(final int pageSize) {

                this.pageSize = pageSize;
        }

        public int getEndRow() {

                return page * pageSize;
        }

        public int getNextRow() {

                return 10;
        }


        public void setStatus(Status status) {

                this.status = status;
        }

        public Status getStatus() {

                return this.status;
        }


}
