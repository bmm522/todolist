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

    @PostMapping
    public ApiResponse<Integer> insertTodo(
            @RequestBody @Valid TodoInsertClientRequest request,
            @CurrentUserId Long userId) {
        TodoInsertRequest dto = TodoServiceDtoConverter.of(request, userId);
        return ApiResponse.success(HttpStatus.CREATED, todoService.insertTodo(dto));
    }

    @GetMapping("/list")
    public ApiResponse<TodoGetResponse> getTodoList(
            @RequestParam("page") int page,
            @RequestParam("isUpdate") Status isUpdate,
            @RequestParam("isGetBeforeDataStatus") Status isGetBeforeDataStatus,
            @RequestParam(value = "todoTitle", required = false) Optional<String> todoTitle,
            @RequestParam(value = "todoAt", required = false) Optional<String> todoAt,
            @CurrentUserId Long userId) {
        TodoGetSearchCondition dto = TodoServiceDtoConverter.of(page, isUpdate, isGetBeforeDataStatus, todoTitle, todoAt);
        return ApiResponse.success(HttpStatus.OK, todoService.getTodoGroupingMap(dto, userId));
    }


    @PostMapping("/update")
    public ApiResponse<Integer> updateTodo(
            @RequestBody @Valid TodoUpdateClientRequest request
            , @CurrentUserId Long userId) {
        TodoUpdateRequest dto = TodoServiceDtoConverter.of(request, userId);
        return ApiResponse.success(HttpStatus.OK, todoService.updateTodo(dto));
    }

    @PostMapping("/list/batch-delete")
    public ApiResponse<Integer> batchDeleteTodoList(@RequestBody TodoBatchDeleteClientRequest request, @CurrentUserId Long userId) {
        request.checkListEmpty();
        TodoBatchDeleteRequest dto = TodoServiceDtoConverter.of(request, userId);
        return ApiResponse.success(HttpStatus.OK, todoService.batchDeleteTodoList(dto));
    }

    @PostMapping("/list/done/batch-update/{isDone}")
    public ApiResponse<Integer> batchUpdateTodoDone(@RequestBody TodoBatchUpdateTodoDoneClientRequest request, @PathVariable("isDone") Status isDone, @CurrentUserId Long userId) {
        TodoBatchUpdateTodoDoneRequest dto = TodoServiceDtoConverter.of(request, isDone, userId);
        return ApiResponse.success(HttpStatus.OK, todoService.batchUpdateTodoDone(dto));
    }


}
