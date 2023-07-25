package com.jiinkim.todolist.user.model;



import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User  {



    private Long userId;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }


    public static User createWhenRegister(final String username, final String password) {
        return new User(username, password);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }



}
