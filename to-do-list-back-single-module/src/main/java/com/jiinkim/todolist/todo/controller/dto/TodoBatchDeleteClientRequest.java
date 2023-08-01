package com.jiinkim.todolist.todo.controller.dto;

import com.jiinkim.todolist.common.exception.badrequest.DeleteTodoIdListEmptyException;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoBatchDeleteClientRequest {

    private List<Integer> deletedTodoIdList;

    public void checkListEmpty() {
        if (this.deletedTodoIdList.size() == 0) {
            throw new DeleteTodoIdListEmptyException("삭제되는 todoIdList에는 비어있는 리스트를 요청할 수 없습니다.");
        }

    }

}
