package com.jiinkim.todolist.todo.controller;


import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.common.config.security.CurrentUserId;
import com.jiinkim.todolist.common.dto.ApiResponse;
import com.jiinkim.todolist.todo.controller.dto.TodoDoneUpdateClientRequest;
import com.jiinkim.todolist.todo.controller.dto.TodoListDeleteClientRequest;
import com.jiinkim.todolist.todo.controller.dto.TodoUpdateClientRequest;
import com.jiinkim.todolist.todo.service.dto.TodoDoneUpdateRequest;
import com.jiinkim.todolist.todo.service.dto.TodoGetResponse;
import com.jiinkim.todolist.todo.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.service.TodoService;
import com.jiinkim.todolist.todo.controller.dto.TodoServiceDtoConverter;
import com.jiinkim.todolist.todo.controller.dto.TodoInsertClientRequest;
import com.jiinkim.todolist.todo.service.dto.TodoListDeleteRequest;
import com.jiinkim.todolist.todo.service.dto.TodoUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
            @CurrentUserId Long userId) {
        return ApiResponse.success(HttpStatus.OK, todoService.getTodoGroupingMap(page, isUpdate, userId));
    }

    @PostMapping("/done/update")
    public ApiResponse<Integer> updateTodoDone(
            @RequestBody @Valid TodoDoneUpdateClientRequest request,
            @CurrentUserId Long userId) {
        TodoDoneUpdateRequest dto = TodoServiceDtoConverter.of(request, userId);
        return ApiResponse.success(HttpStatus.OK, todoService.updateTodoDone(dto));
    }

    @PostMapping("/update")
    public ApiResponse<Integer> updateTodo(
        @RequestBody @Valid TodoUpdateClientRequest request
        , @CurrentUserId Long userId) {
        TodoUpdateRequest dto = TodoServiceDtoConverter.of(request, userId);
        return ApiResponse.success(HttpStatus.OK, todoService.updateTodo(dto));
    }

    @PostMapping("/list/delete")
    public ApiResponse<Integer> deleteTodoList(@RequestBody TodoListDeleteClientRequest request, @CurrentUserId Long userId) {
        request.checkListEmpty();
        TodoListDeleteRequest dto = TodoServiceDtoConverter.of(request, userId);
        return ApiResponse.success(HttpStatus.OK, todoService.deleteTodoList(dto));
    }


}
