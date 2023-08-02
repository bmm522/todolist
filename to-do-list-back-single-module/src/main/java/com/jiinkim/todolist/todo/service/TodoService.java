package com.jiinkim.todolist.todo.service;

import com.jiinkim.todolist.todo.service.dto.*;


public interface TodoService {

    /**
     * 할일(Todo)을 등록합니다.
     *
     * @param request 할일 등록 요청 데이터를 담은 TodoInsertRequest 객체
     * @return Integer 등록된 할일의 아이디
     */
    Integer insertTodo(final TodoInsertRequest request);

    /**
     * 할일(Todo)을 업데이트합니다.
     *
     * @param request 업데이트할 할일 데이터를 담은 TodoUpdateRequest 객체
     * @return Integer 업데이트된 할일의 아이디
     */
    Integer updateTodo(TodoUpdateRequest request);

    /**
     * 여러 할일(Todo)을 일괄 삭제합니다.
     *
     * @param request 일괄 삭제할 할일 아이디 리스트를 담은 TodoBatchDeleteRequest 객체
     * @return Integer 삭제된 할일의 개수
     */
    Integer batchDeleteTodoList(TodoBatchDeleteRequest request);

    /**
     * 여러 할일(Todo)의 완료 상태를 일괄 업데이트합니다.
     *
     * @param dto    일괄 업데이트할 할일 아이디 리스트와 업데이트할 완료 상태를 담은 TodoBatchUpdateTodoDoneRequest 객체
     * @return Integer 업데이트된 할일의 개수
     */
    Integer batchUpdateTodoDone(TodoBatchUpdateTodoDoneRequest dto);

    /**
     * 할일(Todo) 목록을 조회하고 그룹화합니다.
     *
     * @param condition 할일 목록 조회 조건을 담은 TodoGetSearchCondition 객체
     * @param userId    현재 사용자의 아이디
     * @return TodoGetResponse 조회된 할일 목록을 그룹화하여 담은 TodoGetResponse 객체
     */
    TodoGetResponse getTodoGroupingMap(final TodoGetSearchCondition condition, final Long userId);


}
