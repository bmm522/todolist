package com.jiinkim.todolist.todo.application.service;

import com.jiinkim.todolist.todo.dao.TodoDao;
import com.jiinkim.todolist.todo.dao.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoServiceTest {
    @Autowired
    TodoDao todoDao;

    @Test
    void dummyInsert() {
        LongStream.rangeClosed(2, 30_000).forEach(i->{
            LocalDateTime dateTime = LocalDateTime.now();
            todoDao.insert(Todo.create(i, "test"+i, "content " + i, dateTime.plusMinutes(i), false,5L, dateTime, dateTime));
        });
    }
}