package com.jiinkim.todolist.common.config.security;

import com.jiinkim.todolist.user.dao.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

        private User user;

        private Long userId;

        private String username;

        public UserDetailsImpl(User user) {

                this.user = user;
        }

        // Security Context 전용
        public UserDetailsImpl(Long userId, String username) {

                this.userId = userId;
        }



        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {

                return null;
        }

        public User getUser() {

                return this.user;
        }

        @Override
        public String getPassword() {

                return user.getPassword();
        }


        // 로그인 시에는 user객체에서, 인증 시에는 username만 반환
        @Override
        public String getUsername() {

                return this.user != null ? user.getUsername() : this.username;
        }

        public Long getUserId() {

                return this.userId;
        }

        @Override
        public boolean isAccountNonExpired() {

                return true;
        }

        @Override
        public boolean isAccountNonLocked() {

                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {

                return true;
        }

        @Override
        public boolean isEnabled() {

                return true;
        }

}
