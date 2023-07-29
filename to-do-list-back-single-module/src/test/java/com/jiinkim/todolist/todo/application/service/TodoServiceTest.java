package com.jiinkim.todolist.todo.application.service;

import com.jiinkim.todolist.common.config.mybatis.Status;
import com.jiinkim.todolist.todo.dao.TodoDao;
import com.jiinkim.todolist.todo.dao.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import java.util.stream.LongStream;


@SpringBootTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoServiceTest {

    @Autowired
    TodoDao todoDao;

    @Test
    void dummyInsert() {
        LongStream.rangeClosed(2, 1_00).forEach(i->{

            LocalDateTime dateTime = LocalDateTime.now();
            todoDao.insert(Todo.createWhenTest(i, "test"+i, "content " + i, dateTime.plusHours(i), Status.N,5L, Status.Y, dateTime, dateTime));
        });
    }
}