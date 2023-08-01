package com.jiinkim.todolist.todo.service;

import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.common.exception.PermissionException;
import com.jiinkim.todolist.common.exception.TodoInsertFailedException;
import com.jiinkim.todolist.common.exception.TodoNotFoundQueryResultException;
import com.jiinkim.todolist.common.exception.UpdateFailedException;
import com.jiinkim.todolist.todo.dao.model.converter.TodoModelConverter;
import com.jiinkim.todolist.todo.dao.query.dto.TodoListGetParams;
import com.jiinkim.todolist.todo.dao.query.dto.converter.TodoQueryDtoConverter;
import com.jiinkim.todolist.todo.service.dto.*;
import com.jiinkim.todolist.todo.dao.TodoDao;
import com.jiinkim.todolist.todo.dao.model.Todo;
import com.jiinkim.todolist.todo.dao.query.dto.TodoQueryDto;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.groupingBy;


@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoDao todoDao;

    @Transactional
    @Override
    public Integer insertTodo(final TodoInsertRequest request) {
        Todo todo = TodoModelConverter.from(request);
        int result = todoDao.insert(todo);
        if (result != 1) {
            throw new TodoInsertFailedException("todo를 저장하는 과정에서 에러");
        }
        return result;
    }

    @Override
    public TodoGetResponse getTodoGroupingMap(final TodoGetSearchCondition condition, final Long userId) {

        TodoListGetParams params = TodoQueryDtoConverter.of(condition, userId);

        List<TodoQueryDto> queryDtoList = todoDao.findAllBySearchCondition(params);

        Map<LocalDate, List<TodoQueryDto>> timeBucketTodoMap = queryDtoList.stream()
                .collect(groupingBy(TodoQueryDto::getTodoDate));

        return TodoGetResponse.create(timeBucketTodoMap);
    }


    @Transactional
    @Override
    public Integer updateTodo(final TodoUpdateRequest request) {
        TodoQueryDto todoQueryDto = todoDao.findByTodoId(request.getTodoId(), Status.Y)
                .orElseThrow(() -> new TodoNotFoundQueryResultException("Todo PK값에 해당 하는 Todo객체가 없습니다."));

        if (!todoQueryDto.isPermission(request.getUserId())) {
            throw new PermissionException("todo를 변경할 권한이 없습니다.");
        }

        Todo todo = TodoModelConverter.from(request);
        int result = todoDao.updateTodo(todo);

        if (result != 1) {
            throw new UpdateFailedException("todo 업데이트 하는 과정에서 에러");
        }

        return result;
    }

    @Transactional
    @Override
    public Integer batchDeleteTodoList(final TodoBatchDeleteRequest request) {
        List<TodoQueryDto> todoQueryDtoList = todoDao.findAllByTodoIdAndUserId(request.getDeletedTodoIdList());

        todoQueryDtoList.stream()
                .filter(todoQueryDto -> !todoQueryDto.isPermission(request.getUserId()))
                .findAny()
                .ifPresent(todoQueryDto -> {
                    throw new PermissionException("todo를 삭제할 권한이 없습니다.");
                });

        return todoDao.batchDeleteTodoListByTodoId(request.getDeletedTodoIdList());
    }

    @Override
    public Integer batchUpdateTodoDone(TodoBatchUpdateTodoDoneRequest request) {
        List<TodoQueryDto> todoQueryDtoList = todoDao.findAllByTodoIdAndUserId(request.getUpdatedTodoIdList());

        todoQueryDtoList.stream()
                .filter(todoQueryDto -> !todoQueryDto.isPermission(request.getUserId()))
                .findAny()
                .ifPresent(todoQueryDto -> {
                    throw new PermissionException("todo의 done 상태를 변경할  권한이 없습니다.");
                });

        System.out.println(request.getIsDone());

        return todoDao.batchUpdateTodoDoneByTodoIdAndIsDone(request.getUpdatedTodoIdList(), request.getIsDone());
    }

}

