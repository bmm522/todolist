//package com.jiinkim.todolist.todo.application.facade;

import com.jiinkim.todolist.todo.application.service.TodoService;
import com.jiinkim.todolist.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


//@Component
//@RequiredArgsConstructor
//public class TodoFacade {
//
//    private final UserService userService;
//
//    private final TodoService todoService;
//
//
//}


// 1. 한번의 join으로 가져온다. (user까지) --> mybatis
// 2. 각각 db를 두번 다녀와서 코드단에서 조합하여 가져온다. --> JPA
