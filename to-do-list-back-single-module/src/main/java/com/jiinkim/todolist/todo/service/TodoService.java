package com.jiinkim.todolist.todo.service;

import com.jiinkim.todolist.common.exception.InsertFailedException;
import com.jiinkim.todolist.todo.dao.query.dto.TodoListGetParams;
import com.jiinkim.todolist.todo.dao.query.dto.TodoQueryDtoConverter;
import com.jiinkim.todolist.todo.service.dto.TodoGetResponse;
import com.jiinkim.todolist.todo.service.dto.TodoInsertRequest;
import com.jiinkim.todolist.todo.dao.TodoDao;
import com.jiinkim.todolist.todo.dao.model.Todo;
import com.jiinkim.todolist.todo.dao.model.TodoModelConverter;
import com.jiinkim.todolist.todo.dao.query.dto.TodoQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoDao todoDao;

    public Integer insert(final TodoInsertRequest dto) {
        Todo todo = TodoModelConverter.from(dto);
        int result = todoDao.insert(todo);
        if(result != 1) {
            throw new InsertFailedException("Inserting Todo data failed");
        }
        return result;
    }

  public TodoGetResponse getTodoList(final int page, final Long userId) {

      TodoListGetParams params = TodoQueryDtoConverter.of(page, userId);

      List<TodoQueryDto> queryDtoList = todoDao.findAllByUserIdWithPaging(params);

      Map<LocalDateTime, List<TodoQueryDto>> timeBucketTodoMap =  queryDtoList.stream()
              .collect(groupingBy(todoAt()));

      return TodoGetResponse.create(timeBucketTodoMap);
    }

    private static Function<TodoQueryDto, LocalDateTime> todoAt() {
        return queryDto -> queryDto.getTodoAt().truncatedTo(ChronoUnit.DAYS);
    }

}

