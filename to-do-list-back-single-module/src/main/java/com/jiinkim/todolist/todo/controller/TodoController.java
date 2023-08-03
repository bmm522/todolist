package com.jiinkim.todolist.todo.controller;


import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.common.config.security.CurrentUserId;
import com.jiinkim.todolist.common.dto.ApiResponse;
import com.jiinkim.todolist.todo.controller.dto.TodoBatchDeleteClientRequest;
import com.jiinkim.todolist.todo.controller.dto.TodoBatchUpdateTodoDoneClientRequest;
import com.jiinkim.todolist.todo.controller.dto.TodoUpdateClientRequest;
import com.jiinkim.todolist.todo.service.TodoService;
import com.jiinkim.todolist.todo.service.dto.*;
import com.jiinkim.todolist.todo.controller.dto.TodoInsertClientRequest;
import com.jiinkim.todolist.todo.service.dto.converter.TodoServiceDtoConverter;
import com.jiinkim.todolist.todo.service.dto.TodoBatchDeleteRequest;
import com.jiinkim.todolist.todo.service.dto.TodoBatchUpdateTodoDoneRequest;
import com.jiinkim.todolist.todo.service.dto.TodoGetSearchCondition;
import com.jiinkim.todolist.todo.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.service.dto.TodoUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
@Slf4j
public class TodoController {

    private final TodoService todoService;

    /**
     * 할일(Todo)을 등록합니다.
     *
     * @param request 할일 등록 요청 데이터를 담은 TodoInsertClientRequest 객체
     * @param userId  현재 사용자의 아이디
     * @return ApiResponse<Integer> 반영된 쿼리 갯수를 담은 ApiResponse 객체
     */
    @PostMapping
    public ApiResponse<Integer> insertTodo(@RequestBody @Valid TodoInsertClientRequest request,
        @CurrentUserId Long userId) {

        TodoInsertRequest dto = TodoServiceDtoConverter.of(request, userId);
        return ApiResponse.success(HttpStatus.CREATED, todoService.insertTodo(dto));
    }

    /**
     * 할일(Todo) 목록을 조회합니다.
     *
     * @param page                  페이지 번호
     * @param isUpdate              할일 업데이트 여부
     * @param isGetBeforeDataStatus 이전 데이터의 상태
     * @param todoTitle             할일 제목 (선택 사항)
     * @param fromTodoAt            할일 시작일 (선택 사항)
     * @param toTodoAt              할일 종료일 (선택 사항)
     * @param userId                현재 사용자의 아이디
     * @return ApiResponse<TodoGetResponse> 조회된 할일 목록을 담은 ApiResponse 객체
     */
    @GetMapping("/list")
    public ApiResponse<TodoGetResponse> getTodoList(@RequestParam("page") int page,
        @RequestParam("isUpdate") Status isUpdate,
        @RequestParam("isGetBeforeDataStatus") Status isGetBeforeDataStatus,
        @RequestParam(value = "todoTitle", required = false) Optional<String> todoTitle,
        @RequestParam(value = "fromTodoAt", required = false) Optional<String> fromTodoAt,
        @RequestParam(value = "toTodoAt", required = false) Optional<String> toTodoAt,
        @CurrentUserId Long userId) {

        TodoGetSearchCondition dto = TodoServiceDtoConverter.of(page, isUpdate,
            isGetBeforeDataStatus, todoTitle, fromTodoAt, toTodoAt);
        return ApiResponse.success(HttpStatus.OK, todoService.getTodoGroupingMap(dto, userId));
    }


    /**
     * 할일(Todo)을 업데이트합니다.
     *
     * @param request 업데이트할 Todo 데이터를 담은 TodoUpdateClientRequest 객체
     * @param userId  현재 사용자의 아이디
     * @return ApiResponse<Integer> 반영된 쿼리 갯수를 담은 ApiResponse 객체
     */
    @PostMapping("/update")
    public ApiResponse<Integer> updateTodo(@RequestBody @Valid TodoUpdateClientRequest request,
        @CurrentUserId Long userId) {

        TodoUpdateRequest dto = TodoServiceDtoConverter.of(request, userId);
        return ApiResponse.success(HttpStatus.OK, todoService.updateTodo(dto));
    }

    /**
     * 여러 할일(Todo)을 일괄 삭제합니다.
     *
     * @param request 일괄 삭제할 Todo 아이디 리스트를 담은 TodoBatchDeleteClientRequest 객체
     * @param userId  현재 사용자의 아이디
     * @return ApiResponse<Integer> 반영된 쿼리 갯수를 담은 ApiResponse 객체
     */
    @PostMapping("/list/batch-delete")
    public ApiResponse<Integer> batchDeleteTodoList(
        @RequestBody TodoBatchDeleteClientRequest request, @CurrentUserId Long userId) {

        request.checkListEmpty();
        TodoBatchDeleteRequest dto = TodoServiceDtoConverter.of(request, userId);
        return ApiResponse.success(HttpStatus.OK, todoService.batchDeleteTodoList(dto));
    }

    /**
     * 여러 할일(Todo)의 완료 상태를 일괄 업데이트합니다.
     *
     * @param request 일괄 업데이트할 할일 아이디 리스트와 업데이트할 완료 상태를 담은 TodoBatchUpdateTodoDoneClientRequest 객체
     * @param isDone  Todo 완료 상태
     * @param userId  현재 사용자의 아이디
     * @return ApiResponse<Integer> 반영된 쿼리 갯수를 담은 ApiResponse 객체
     */
    @PostMapping("/list/done/batch-update/{isDone}")
    public ApiResponse<Integer> batchUpdateTodoDone(
        @RequestBody TodoBatchUpdateTodoDoneClientRequest request,
        @PathVariable("isDone") Status isDone, @CurrentUserId Long userId) {

        TodoBatchUpdateTodoDoneRequest dto = TodoServiceDtoConverter.of(request, isDone, userId);
        return ApiResponse.success(HttpStatus.OK, todoService.batchUpdateTodoDone(dto));
    }


}
