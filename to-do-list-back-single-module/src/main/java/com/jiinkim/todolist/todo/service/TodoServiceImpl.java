package com.jiinkim.todolist.todo.service;

import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.common.exception.forbidden.PermissionException;
import com.jiinkim.todolist.common.exception.servererror.TodoInsertFailedException;
import com.jiinkim.todolist.common.exception.servererror.TodoNotFoundQueryResultException;
import com.jiinkim.todolist.common.exception.servererror.TodoUpdateFailedException;
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

        /**
         * 할일(Todo)을 등록합니다.
         *
         * @param request Todo 등록 요청 데이터를 담은 TodoInsertRequest 객체
         * @return Integer 반영된 쿼리의 갯수
         * @throws TodoInsertFailedException Todo 등록에 실패한 경우 발생합니다.
         */
        @Transactional
        @Override
        public Integer insertTodo(TodoInsertRequest request) {

                Todo todo = TodoModelConverter.from(request);
                int result = todoDao.insert(todo);
                if (result != 1) {
                        throw new TodoInsertFailedException("todo를 저장하는 과정에서 에러");
                }
                return result;
        }

        /**
         * 할일(Todo) 목록을 조회하고 그룹화합니다.
         *
         * @param condition Todo 목록 조회 조건을 담은 TodoGetSearchCondition 객체
         * @param userId    현재 사용자의 아이디
         * @return TodoGetResponse 조회된 Todo 목록을 그룹화하여 담은 TodoGetResponse 객체
         */
        @Override
        public TodoGetResponse getTodoGroupingMap(final TodoGetSearchCondition condition,
            final Long userId) {

                TodoListGetParams params = TodoQueryDtoConverter.of(condition, userId);

                List<TodoQueryDto> queryDtoList = todoDao.findAllBySearchCondition(params);

                Map<LocalDate, List<TodoQueryDto>> timeBucketTodoMap = queryDtoList.stream()
                    .collect(groupingBy(TodoQueryDto::getTodoDate));

                return TodoGetResponse.create(timeBucketTodoMap);
        }

        /**
         * 할일(Todo)을 업데이트합니다.
         *
         * @param request 업데이트할 할일 데이터를 담은 TodoUpdateRequest 객체
         * @return Integer 반영된 쿼리의 갯수
         * @throws TodoUpdateFailedException Todo 업데이트에 실패한 경우 발생합니다.
         */
        @Transactional
        @Override
        public Integer updateTodo(TodoUpdateRequest request) {

                TodoQueryDto todoQueryDto = todoDao.findByTodoId(request.getTodoId(), Status.Y)
                    .orElseThrow(() -> new TodoNotFoundQueryResultException(
                        "Todo PK값에 해당 하는 Todo객체가 없습니다."));

                checkPermission(todoQueryDto, request.getUserId());

                Todo todo = TodoModelConverter.from(request);
                int result = todoDao.updateTodo(todo);

                if (result != 1) {
                        throw new TodoUpdateFailedException("todo 업데이트 하는 과정에서 에러");
                }

                return result;
        }

        /**
         * 여러 할일(Todo)을 일괄 삭제합니다.
         *
         * @param request 일괄 삭제할 할일 아이디 리스트를 담은 TodoBatchDeleteRequest 객체
         * @return Integer 삭제된 할일의 개수
         * @throws PermissionException Todo 삭제 권한이 없는 경우 발생합니다.
         */
        @Transactional
        @Override
        public Integer batchDeleteTodoList(final TodoBatchDeleteRequest request) {

                List<TodoQueryDto> todoQueryDtoList = todoDao.findAllByTodoIdAndUserId(
                    request.getDeletedTodoIdList());

                checkPermission(todoQueryDtoList, request.getUserId());

                return todoDao.batchDeleteTodoListByTodoId(request.getDeletedTodoIdList());

        }

        /**
         * 여러 할일(Todo)의 완료 상태를 일괄 업데이트합니다.
         *
         * @param request 일괄 업데이트할 할일 아이디 리스트와 업데이트할 완료 상태를 담은 TodoBatchUpdateTodoDoneRequest 객체
         * @return Integer 업데이트된 할일의 개수
         * @throws PermissionException Todo 업데이트 권한이 없는 경우 발생합니다.
         */
        @Transactional
        @Override
        public Integer batchUpdateTodoDone(TodoBatchUpdateTodoDoneRequest request) {

                List<TodoQueryDto> todoQueryDtoList = todoDao.findAllByTodoIdAndUserId(
                    request.getUpdatedTodoIdList());

                checkPermission(todoQueryDtoList, request.getUserId());

                return todoDao.batchUpdateTodoDoneByTodoIdAndIsDone(request.getUpdatedTodoIdList(),
                    request.getIsDone());

        }

        private void checkPermission(final TodoQueryDto todoQueryDto, final Long userIdFromRequest) {

                if (!todoQueryDto.isPermission(userIdFromRequest)) {
                        throw new PermissionException("todo를 변경할 권한이 없습니다.");
                }
        }

        private void checkPermission(final List<TodoQueryDto> todoQueryDtoList,
            final Long userIdFromRequest) {

                todoQueryDtoList.stream()
                    .filter(todoQueryDto -> !todoQueryDto.isPermission(userIdFromRequest))
                    .findAny()
                    .ifPresent(todoQueryDto -> {
                            throw new PermissionException("todo를 변경할 권한이 없습니다.");
                    });
        }


}

