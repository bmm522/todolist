package com.jiinkim.todolist.user.model;



import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
public class User {

    private Long userId;
    private String username;
    private String password;
    private Date createdAt;
    private Date updatedAt;
}
