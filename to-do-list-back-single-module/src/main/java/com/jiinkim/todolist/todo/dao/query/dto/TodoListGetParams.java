package com.jiinkim.todolist.todo.dao.query.dto;

import com.jiinkim.todolist.common.config.mybatis.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Alias("todoListGetParams")
public class TodoListGetParams {

  private final int page;
  private int pageSize;
  private final Long userId;

  private Status isUpdate;
  private Status status;




  public int getOffset() {
    return (this.page - 1) * this.pageSize;
  }

  private TodoListGetParams(final int page, final Long userId, final Status isUpdate) {
    this.page = page;
    this.pageSize = 10;
    this.userId = userId;
    this.status = Status.Y;
    this.isUpdate = isUpdate;
  }

  public static TodoListGetParams create(final int page, final Long userId, final Status isUpdate) {
    return new TodoListGetParams(page, userId, isUpdate);
  }

  public int getStartRow() {
    return  (page -1) * pageSize + 1;
  }

public String getIsUpdate() {
    return isUpdate.toString();
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
