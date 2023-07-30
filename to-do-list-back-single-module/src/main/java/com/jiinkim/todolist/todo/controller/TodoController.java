package com.jiinkim.todolist.todo.controller;



import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.common.config.security.CurrentUserId;
import com.jiinkim.todolist.common.dto.ApiResponse;
import com.jiinkim.todolist.todo.controller.dto.TodoDoneUpdateClientRequest;
import com.jiinkim.todolist.todo.dao.query.dto.TodoQueryDto;
import com.jiinkim.todolist.todo.service.dto.TodoDoneUpdateRequest;
import com.jiinkim.todolist.todo.service.dto.TodoGetResponse;
import com.jiinkim.todolist.todo.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.service.TodoService;
import com.jiinkim.todolist.todo.controller.dto.TodoServiceDtoConverter;
import com.jiinkim.todolist.todo.controller.dto.TodoInsertClientRequest;
import com.jiinkim.todolist.user.service.dto.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ApiResponse<Integer> insert(
            @RequestBody @Valid TodoInsertClientRequest request,
            @CurrentUserId Long userId) {
        TodoInsertRequest dto = TodoServiceDtoConverter.of(request, userId);
        return ApiResponse.success(HttpStatus.CREATED, todoService.insert(dto));
    }

    @GetMapping("/list")
    public ApiResponse<TodoGetResponse> getTodoList(@RequestParam("page")int page, @RequestParam("isUpdate")
        Status isUpdate
        , @CurrentUserId Long userId) {
        return ApiResponse.success(HttpStatus.OK, todoService.getTodoList(page, isUpdate, userId));
    }

    @PostMapping("/done/update")
    public ApiResponse<Integer> updateTodoDone(
        @RequestBody @Valid TodoDoneUpdateClientRequest request,
        @CurrentUserId Long userId) {
        TodoDoneUpdateRequest dto = TodoServiceDtoConverter.of(request, userId );
        return ApiResponse.success(HttpStatus.OK, todoService.updateTodoDone(dto));
    }







}
