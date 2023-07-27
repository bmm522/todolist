package com.jiinkim.todolist.todo.dao.query.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoGetRequest {

  private final Long page;
  private Long pageSize;

  private final Long userId;

  public Long getOffset() {
    return (this.page - 1) * this.pageSize;
  }

  private TodoGetRequest(final Long page, final Long userId) {
    this.page = page;
    this.pageSize = 10L;
    this.userId = userId;
  }

  public static TodoGetRequest create(final Long page, final Long userId) {
    return new TodoGetRequest(page, userId);
  }

  public void setPageSize(final Long pageSize) {
    this.pageSize = pageSize;
  }

}
