package com.jiinkim.todolist.user.dao.model;



import com.jiinkim.todolist.common.utils.TimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User  {



    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private String refreshToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    private User(final String username, final String password, final String nickname, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private User(final Long userId, final String username, final String password, final String nickname, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User create(final Long userId, final String username, final String password, final String nickname, final String refreshToken,final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        return new User(userId, username, password, nickname, refreshToken,createdAt, updatedAt);
    }


    public static User createWhenRegister(final String username, final String password, final String nickname) {
        LocalDateTime now = TimeUtils.getNow();
        return new User(username, password, nickname, now, now);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
