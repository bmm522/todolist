package com.jiinkim.todolist.todo.controller;



import com.jiinkim.todolist.common.dto.ApiResponse;
import com.jiinkim.todolist.todo.application.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.application.service.TodoService;
import com.jiinkim.todolist.todo.controller.dto.TodoInsertClientRequest;
import com.jiinkim.todolist.todo.controller.util.TodoClientDtoConverter;
import com.jiinkim.todolist.user.service.dto.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ApiResponse<Integer> insert(
            @RequestBody @Valid TodoInsertClientRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoInsertRequest dto = TodoClientDtoConverter.toTodoInsertDto(request, userDetails.getUserId());
        return ApiResponse.success(HttpStatus.CREATED, todoService.insert(dto));
    }







}
