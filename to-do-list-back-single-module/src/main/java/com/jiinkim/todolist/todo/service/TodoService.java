package com.jiinkim.todolist.todo.service;

import com.jiinkim.todolist.todo.service.dto.*;


public interface TodoService {
    Integer insertTodo(final TodoInsertRequest request);

    Integer updateTodo(final TodoUpdateRequest request);

    Integer batchDeleteTodoList(final TodoBatchDeleteRequest request);

    Integer batchUpdateTodoDone(TodoBatchUpdateTodoDoneRequest dto);

    TodoGetResponse getTodoGroupingMap(final TodoGetSearchCondition condition, final Long userId);


}
